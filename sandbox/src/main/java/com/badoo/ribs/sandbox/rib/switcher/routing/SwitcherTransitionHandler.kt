package com.badoo.ribs.sandbox.rib.switcher.routing

import android.view.animation.OvershootInterpolator
import com.badoo.ribs.core.routing.transition.effect.helper.AnimationContainer
import com.badoo.ribs.core.routing.transition.effect.SharedElementTransition
import com.badoo.ribs.core.routing.transition.handler.CrossFader
import com.badoo.ribs.core.routing.transition.handler.SharedElements
import com.badoo.ribs.core.routing.transition.handler.TabSwitcher
import com.badoo.ribs.core.routing.transition.handler.TransitionHandler
import com.badoo.ribs.sandbox.R

@SuppressWarnings("MagicNumber")
class SwitcherTransitionHandler(duration: Long): TransitionHandler.Multiple<SwitcherRouter.Configuration>(
    listOf<TransitionHandler<SwitcherRouter.Configuration>>(

        TabSwitcher(
            animationContainer = AnimationContainer.RootView,
            duration = duration,
            tabsOrder = listOf(
                SwitcherRouter.Configuration.Content.Hello,
                SwitcherRouter.Configuration.Content.Foo,
                SwitcherRouter.Configuration.Content.DialogsExample
            )
        ),

        SharedElements(
            params = listOf(
                SharedElementTransition.Params(
                    duration = duration,
                    findExitingElement = { it.findViewById(R.id.sharedElementSquare) },
                    findEnteringElement = { it.findViewById(R.id.sharedElementSquare) },
                    translateXInterpolator = OvershootInterpolator(),
                    translateYInterpolator = OvershootInterpolator(14f),
                    rotation = SharedElementTransition.RotationParams(0.75f * 360)
                )
            )
        ),

        CrossFader(
            duration = duration
        )
    )
)
