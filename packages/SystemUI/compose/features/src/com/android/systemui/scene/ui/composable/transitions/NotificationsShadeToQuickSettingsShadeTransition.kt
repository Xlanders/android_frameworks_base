/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.systemui.scene.ui.composable.transitions

import androidx.compose.animation.core.tween
import com.android.compose.animation.scene.Edge
import com.android.compose.animation.scene.TransitionBuilder
import com.android.systemui.notifications.ui.composable.NotificationsShade
import com.android.systemui.qs.ui.composable.QuickSettingsShade
import kotlin.time.Duration.Companion.milliseconds

/**
 * Moves between the dual shade panels like pages: notifications leave towards the start edge
 * while quick settings come in from the end edge. Played in reverse for the other direction. The
 * shared scrim stays in place.
 */
fun TransitionBuilder.notificationsShadeToQuickSettingsShadeTransition(
    durationScale: Double = 1.0
) {
    spec = tween(durationMillis = (DefaultDuration * durationScale).inWholeMilliseconds.toInt())

    translate(NotificationsShade.Elements.Panel, Edge.Start)
    translate(QuickSettingsShade.Elements.Panel, Edge.End)

    fractionRange(end = .5f) { fade(NotificationsShade.Elements.StatusBar) }
    fractionRange(start = .5f) { fade(QuickSettingsShade.Elements.StatusBar) }
}

private val DefaultDuration = 350.milliseconds
