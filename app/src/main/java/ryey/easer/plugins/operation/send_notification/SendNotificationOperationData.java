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

package ryey.easer.plugins.operation.send_notification;

import android.os.Parcel;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

import ryey.easer.Utils;
import ryey.easer.commons.C;
import ryey.easer.commons.IllegalStorageDataException;
import ryey.easer.commons.plugindef.operationplugin.OperationData;

public class SendNotificationOperationData implements OperationData {
    private static final String K_TITLE = "title";
    private static final String K_CONTENT = "content";

    NotificationContent notificationContent;

    SendNotificationOperationData() {
    }

    SendNotificationOperationData(NotificationContent content) {
        notificationContent = content;
    }
    
    SendNotificationOperationData(@NonNull String data, @NonNull C.Format format, int version) throws IllegalStorageDataException {
        parse(data, format, version);
    }

    @Override
    public void parse(XmlPullParser parser, int version) throws IOException, XmlPullParserException, IllegalStorageDataException {
        throw new IllegalAccessError();
    }

    @Override
    public void serialize(XmlSerializer serializer) throws IOException {
        throw new IllegalAccessError();
    }

    @Override
    public void parse(@NonNull String data, @NonNull C.Format format, int version) throws IllegalStorageDataException {
        notificationContent = new NotificationContent();
        switch (format) {
            default:
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    notificationContent.title = jsonObject.getString(K_TITLE);
                    notificationContent.content = jsonObject.getString(K_CONTENT);
                } catch (JSONException e) {
                    throw new IllegalStorageDataException(e.getMessage());
                }
        }
    }

    @NonNull
    @Override
    public String serialize(@NonNull C.Format format) {
        String res;
        switch (format) {
            default:
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(K_TITLE, notificationContent.title);
                    jsonObject.put(K_CONTENT, notificationContent.content);
                } catch (JSONException e) {
                    throw new IllegalStateException(e);
                }
                res = jsonObject.toString();
        }
        return res;
    }

    @SuppressWarnings({"SimplifiableIfStatement", "RedundantIfStatement"})
    @Override
    public boolean isValid() {
        if (notificationContent == null)
            return false;
        if (notificationContent.title == null)
            return false;
        if (notificationContent.content == null)
            return false;
        return true;
    }

    @SuppressWarnings({"SimplifiableIfStatement", "RedundantIfStatement"})
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof SendNotificationOperationData))
            return false;
        if (!((SendNotificationOperationData) obj).isValid())
            return false;
        if (!Utils.nullableEqual(notificationContent.title,
                ((SendNotificationOperationData) obj).notificationContent.title))
            return false;
        if (!Utils.nullableEqual(notificationContent.content,
                ((SendNotificationOperationData) obj).notificationContent.content))
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(notificationContent.title);
        parcel.writeString(notificationContent.content);
    }

    public static final Creator<SendNotificationOperationData> CREATOR
            = new Creator<SendNotificationOperationData>() {
        public SendNotificationOperationData createFromParcel(Parcel in) {
            return new SendNotificationOperationData(in);
        }

        public SendNotificationOperationData[] newArray(int size) {
            return new SendNotificationOperationData[size];
        }
    };

    private SendNotificationOperationData(Parcel in) {
        notificationContent = new NotificationContent();
        notificationContent.title = in.readString();
        notificationContent.content = in.readString();
    }

}
