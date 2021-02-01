// Copyright 2020 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

/// The status of the location service authorization.
enum LocationAuthorizationStatus {
  /// The authorization of the location service is not determined.
  notDetermined,

  /// This app is not authorized to use location.
  restricted,

  /// User explicitly denied the location service.
  denied,

  /// User authorized the app to access the location at any time.
  authorizedAlways,

  /// User authorized the app to access the location when the app is visible to them.
  authorizedWhenInUse,

  /// Status unknown.
  unknown
}

/// the WiFi frequency type.
enum WiFiFreqType {
  /// 2.4G WiFi
  freq2_4G,
  /// 5G WiFi
  freq5G
}