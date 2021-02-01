// Copyright 2020 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.plugins.wifi_info_flutter;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.RequiresApi;
import android.os.Build;

/** Reports wifi information. */
class WifiInfoFlutter {
  private WifiManager wifiManager;

  WifiInfoFlutter(WifiManager wifiManager) {
    this.wifiManager = wifiManager;
  }

  String getWifiName() {
    final WifiInfo wifiInfo = getWifiInfo();
    String ssid = null;
    if (wifiInfo != null) ssid = wifiInfo.getSSID();
    if (ssid != null) ssid = ssid.replaceAll("\"", ""); // Android returns "SSID"
    if (ssid != null && ssid.equals("<unknown ssid>")) ssid = null;
    return ssid;
  }

  String getWifiBSSID() {
    final WifiInfo wifiInfo = getWifiInfo();
    String bssid = null;
    if (wifiInfo != null) {
      bssid = wifiInfo.getBSSID();
    }
    return bssid;
  }

  List<String> wifiFreqType() {
    final WifiInfo wifiInfo = getWifiInfo();
    List<String> list = new ArrayList();

    if (wifiInfo != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      int freq = wifiInfo.getFrequency();
      boolean is24G = freq > 2000 && freq < 3000;
      boolean is5G = freq > 4000 && freq < 6000;

      if (is24G) list.add("2.4");
      if (is5G) list.add("5");
    }

    return list;
  }


  Integer wifiFreq() {
    final WifiInfo wifiInfo = getWifiInfo();

    if (wifiInfo != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      return wifiInfo.getFrequency();
    }

    return null;
  }

  String getWifiIPAddress() {
    WifiInfo wifiInfo = null;
    if (wifiManager != null) wifiInfo = wifiManager.getConnectionInfo();

    String ip = null;
    int i_ip = 0;
    if (wifiInfo != null) i_ip = wifiInfo.getIpAddress();

    if (i_ip != 0)
      ip =
          String.format(
              "%d.%d.%d.%d",
              (i_ip & 0xff), (i_ip >> 8 & 0xff), (i_ip >> 16 & 0xff), (i_ip >> 24 & 0xff));

    return ip;
  }

  private WifiInfo getWifiInfo() {
    return wifiManager == null ? null : wifiManager.getConnectionInfo();
  }
}
