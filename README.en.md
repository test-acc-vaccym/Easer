Easer [![Build Status](https://travis-ci.org/renyuneyun/Easer.svg?branch=master)](https://travis-ci.org/renyuneyun/Easer) [![codecov](https://codecov.io/gh/renyuneyun/Easer/branch/master/graph/badge.svg)](https://codecov.io/gh/renyuneyun/Easer)
=======
[<img src="https://f-droid.org/badge/get-it-on.png"
      alt="Get it on F-Droid"
      height="60">](https://f-droid.org/app/ryey.easer)
<img align="right" src='./app/src/main/ic_launcher-web.png' width='128' height='128'/>

Ease your life by automatically performing routine actions.

Introduction
-----
Make your smart phone smarter: tell it what to do under different situations.

### Smart automation

Easer is an event-driven Android automation tool. It knows various (see below) events and YOU (the user) tell it what to do on what event (and even combine multiple events). Routine actions no longer need to be manually taken.

You can think Easer as a local version of IFTTT: Trigger actions or change settings (*Operation*s, bundled as *Profile*) in different situations (*Event*).

### Inter-app coordination

Easer is also a coordinator of inter-app actions (e.g. communications) -- send custom `Broadcast`s when receiving certain `Broadcast` (designed by YOU).

`Broadcast` (together with `Intent`) is the way Android provides for inter-app communication and signaling. Android doesn't do any carding nor provide any customization to this, so Easer would give it a try.

### Custom Events

You can chain *Event*s (set dependencies) as trees. This mechanism allows Easer to (somewhat) organize Events using boolean logics ("and", "or").

Currently, Easer will perform Post-Order Traversal to load you Profile(s). In the near future, Easer will have more meticulous, more expressive and more intuitive categorization of *Event*s -- all these are controlled by YOU.

Also have a look at [wiki](https://github.com/renyuneyun/Easer/wiki), especially [FAQ](https://github.com/renyuneyun/Easer/wiki/FAQ).

Examples
------
* Turn your phone into Silent mode at 2 a.m.
* Cancel Silent mode at 8 a.m. on weekdays, and at 10 a.m. on weekends
* Turn WiFi on when approaching your home; turn WiFi off when leaving your home

Supported functions
--------
Easer supports listening to many Android events (e.g. date/time, system status) and more (e.g. calendar); the supported operations include but not limited to changing Android settings, sending messages, executing commands.

For a list of the features already supported, see [this page](https://renyuneyun.github.io/Easer/en/FEATURES).

Extending Easer
------
Extending the functionality of Easer (by adding more *Event*s or *Operation*s) is really simple (and is becoming simpler).

Details are described in [this document](https://renyuneyun.github.io/Easer/en/EXTEND).

Support Easer
------
### Raising issues, Comment on issues and Solving issues
If you encounter problems when using Easer, you could raise an issue. It is better (but not necessary) if you could give more details at first so I can pin it faster.  
You could also open an issue if you think that some functions may need to be included in Easer.

It is also welcome to comment on existing issues, either you believe you have the same problem (or idea), you could provide more information about it, or you don't agree with that. Discussion is always welcome.

In some situation (if you are a developer), you may possess the knowledge and time to solve some issues. You could fork the repo, do coding, and create pull requests so you code could be merged to the trunk and you get appreciated by others and also listed in the *Contributors* list.  
It is also very welcome to create pull requests for issues not raised by others, but please first create an issue describing what you want to do (and that you are going to do it).

### Donation

If you would like to make any amount of donation, please see [DONATE](https://renyuneyun.github.io/Easer/en/DONATE).

Any amount of help is appreciated.

Copyright
------
Copyright (c) 2016 - 2017 Rui Zhao (renyuneyun) <renyuneyun@gmail.com>

Licensed under GPLv3+ (See LICENSE)

### Why GPL?

The expected functions of Easer contain lots of tracks / captures of privacy (e.g. location, calendar), and would be able to access the Internet. We would never want a tool which is expected to better facilitate our lives to become a spying tool, so we must prevent that from happening as we can. The only way is to allow anyone to censor every part of Easer, which means to ensure Easer (and its derivated work, if any) to be opensource.  
Because as of the design of Easer, each functionality will (in the furutre) become modules / plugins, maicious codes should also be prohibited from these parts, so we hope to rely on the feature of GPL (that derivated work should also be licensed under GPL) to enforce this.

In fact, ensuring derivated work / plugins to be **GPL** is unneeded, because we only need them to be **opensource**. However, GPL is the only license (known by me) which can enforce deriavted work / plugins to be opensource, so it's the only choice.

Third-party library
-----
* [Logger](https://github.com/orhanobut/logger): Apache License v2
* [android-flowlayout](https://github.com/ApmeM/android-flowlayout): Apache License v2
* [Guava](https://github.com/google/guava): Apache License v2
