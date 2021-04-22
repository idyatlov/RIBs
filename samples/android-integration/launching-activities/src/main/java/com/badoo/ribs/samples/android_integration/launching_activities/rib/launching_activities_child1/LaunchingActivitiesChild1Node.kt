package com.badoo.ribs.samples.android_integration.launching_activities.rib.launching_activities_child1

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.samples.android_integration.launching_activities.rib.launching_activities_child_common.LaunchingActivitiesChildView

class LaunchingActivitiesChild1Node(
        buildParams: BuildParams<*>,
        viewFactory: ViewFactory<LaunchingActivitiesChildView>?,
        plugins: List<Plugin>
) : Node<LaunchingActivitiesChildView>(
        buildParams = buildParams,
        viewFactory = viewFactory,
        plugins = plugins
), LaunchingActivitiesChild1
