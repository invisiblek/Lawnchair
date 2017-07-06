/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.deletescape.lawnchair.config;

import android.content.Context;

import ch.deletescape.lawnchair.Utilities;

/**
 * Defines a set of flags used to control various launcher behaviors
 */
public final class FeatureFlags {

    private static final String KEY_PREF_LIGHT_STATUS_BAR = "pref_lightStatusBar";
    private static final String KEY_PREF_PINCH_TO_OVERVIEW = "pref_pinchToOverview";
    private static final String KEY_PREF_PULLDOWN_NOTIS = "pref_pulldownNotis";
    private static final String KEY_PREF_HOTSEAT_EXTRACTED_COLORS = "pref_hotseatShouldUseExtractedColors";
    private static final String KEY_PREF_HAPTIC_FEEDBACK = "pref_enableHapticFeedback";
    private static final String KEY_PREF_KEEP_SCROLL_STATE = "pref_keepScrollState";
    private static final String KEY_FULL_WIDTH_SEARCHBAR = "pref_fullWidthSearchbar";
    private static final String KEY_SHOW_PIXEL_BAR = "pref_showPixelBar";
    private static final String KEY_HOME_OPENS_DRAWER = "pref_homeOpensDrawer";
    public static final String KEY_SHOW_VOICE_SEARCH_BUTTON = "pref_showMic";
    private static final String KEY_PREF_PIXEL_STYLE_ICONS = "pref_pixelStyleIcons";
    private static final String KEY_PREF_HIDE_APP_LABELS = "pref_hideAppLabels";
    private static final String KEY_PREF_FULL_WIDTH_WIDGETS = "pref_fullWidthWidgets";
    private static final String KEY_PREF_SHOW_NOW_TAB = "pref_showGoogleNowTab";
    private static final String KEY_PREF_TRANSPARENT_HOTSEAT = "pref_isHotseatTransparent";
    private static final String KEY_PREF_ENABLE_DYNAMIC_UI = "pref_enableDynamicUi";

    private FeatureFlags() {
    }

    // When enabled fling down gesture on the first workspace triggers search.
    public static boolean pulldownOpensNotifications(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_PULLDOWN_NOTIS, true);
        return enabled;
    }

    public static boolean pinchToOverview(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_PINCH_TO_OVERVIEW, true);
        return enabled;
    }

    // When enabled the status bar may show dark icons based on the top of the wallpaper.
    public static boolean lightStatusBar(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_LIGHT_STATUS_BAR, false);
        return enabled;
    }

    public static boolean hotseatShouldUseExtractedColors(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_HOTSEAT_EXTRACTED_COLORS, true);
        return enabled;
    }

    public static boolean enableHapticFeedback(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_HAPTIC_FEEDBACK, false);
        return enabled;
    }

    public static boolean keepScrollState(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_KEEP_SCROLL_STATE, false);
        return enabled;
    }

    public static boolean useFullWidthSearchbar(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_FULL_WIDTH_SEARCHBAR, false);
        return enabled;
    }

    public static boolean showVoiceSearchButton(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_SHOW_VOICE_SEARCH_BUTTON, false);
        return enabled;
    }

    public static boolean showPixelBar(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_SHOW_PIXEL_BAR, true);
        return enabled;
    }

    public static boolean homeOpensDrawer(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_HOME_OPENS_DRAWER, true);
        return enabled;
    }

    public static boolean usePixelIcons(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_PIXEL_STYLE_ICONS, true);
        return enabled;
    }

    public static boolean hideAppLabels(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_HIDE_APP_LABELS, false);
        return enabled;
    }

    public static boolean allowFullWidthWidgets(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_FULL_WIDTH_WIDGETS, false);
        return enabled;
    }

    public static boolean showGoogleNowTab(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_SHOW_NOW_TAB, true);
        return enabled;
    }

    public static boolean isTransparentHotseat(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_TRANSPARENT_HOTSEAT, false);
        return enabled;
    }

    public static boolean isDynamicUiEnabled(Context context) {
        boolean enabled = Utilities.getPrefs(context).getBoolean(KEY_PREF_ENABLE_DYNAMIC_UI, false);
        return enabled;
    }
}
