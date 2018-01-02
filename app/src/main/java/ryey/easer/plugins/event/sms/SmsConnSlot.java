/*
 * Copyright (c) 2016 - 2017 Rui Zhao <renyuneyun@gmail.com>
 *
 * This file is part of Easer.
 *
 * Easer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Easer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Easer.  If not, see <http://www.gnu.org/licenses/>.
 */

package ryey.easer.plugins.event.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsMessage;

import com.orhanobut.logger.Logger;

import ryey.easer.Utils;
import ryey.easer.commons.plugindef.ValidData;
import ryey.easer.commons.plugindef.eventplugin.AbstractSlot;
import ryey.easer.commons.plugindef.eventplugin.EventType;

public class SmsConnSlot extends AbstractSlot<SmsEventData> {
    private SmsInnerData smsInnerData = null;
    private EventType type = null;

    private final BroadcastReceiver connReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
                try {
                    Bundle bundle = intent.getExtras();
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    SmsMessage[] msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        String msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                        if (!Utils.isBlank(smsInnerData.sender)) {
                            if (!PhoneNumberUtils.compare(context, msg_from, smsInnerData.sender)) {
                                continue;
                            }
                        }
                        if (!Utils.isBlank(smsInnerData.content)) {
                            if (!msgBody.contains(smsInnerData.content)) {
                                continue;
                            }
                        }
                        changeSatisfiedState(true);
                        return;
                    }
                    changeSatisfiedState(false);
                } catch (Exception e) {
                    Logger.d("Exception caught",e.getMessage());
                }
            }
        }
    };

    private IntentFilter filter;

    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        } else {
            filter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        }
        setRetriggerable(true);
    }

    public SmsConnSlot(Context context) {
        super(context);
    }

    @Override
    public void set(@ValidData @NonNull SmsEventData data) {
        smsInnerData = data.innerData;
        type = data.type();
    }

    @Override
    public void listen() {
        context.registerReceiver(connReceiver, filter);
    }

    @Override
    public void cancel() {
        context.unregisterReceiver(connReceiver);
    }

    @Override
    public void check() {
        // Empty method as expected
    }

    @Override
    public boolean canPromoteSub() {
        if (type == EventType.is) {
            return false;
        } else if (type == EventType.after) {
            return true;
        }
        throw new IllegalAccessError();
    }

}
