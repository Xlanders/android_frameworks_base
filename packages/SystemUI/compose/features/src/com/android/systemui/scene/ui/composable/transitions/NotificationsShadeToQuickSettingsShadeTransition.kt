/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.systemui.scene.ui.composable.transitions

import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.dp
import com.android.compose.animation.scene.Edge
import com.android.compose.animation.scene.TransitionBuilder
import com.android.systemui.notifications.ui.composable.NotificationsShade
import com.android.systemui.qs.ui.composable.QuickSettingsShade
import kotlin.time.Duration.Companion.milliseconds

/**
 * Moves between the dual shade panels like pages: notifications leave towards the start edge
 * while quick settings come in from the end edge. Played in reverse for the other direction. The
 * shared scrim and the status bar chips stay in place.
 */
fun TransitionBuilder.notificationsShadeToQuickSettingsShadeTransition(
    durationScale: Double = 1.0
) {
    spec = tween(durationMillis = (DefaultDuration * durationScale).inWholeMilliseconds.toInt())

    translate(NotificationsShade.Elements.Panel, Edge.Start)
    translate(QuickSettingsShade.Elements.Panel, Edge.End)

    // Both panels show the same status bar chips: keep them still instead of following the panels,
    // and only swap them at the very end so that they don't flicker.
    translate(NotificationsShade.Elements.StatusBar, x = 0.dp)
    translate(QuickSettingsShade.Elements.StatusBar, x = 0.dp)
    fractionRange(start = .99f) {
        fade(NotificationsShade.Elements.StatusBar)
        fade(QuickSettingsShade.Elements.StatusBar)
    }
}

private val DefaultDuration = 350.milliseconds
