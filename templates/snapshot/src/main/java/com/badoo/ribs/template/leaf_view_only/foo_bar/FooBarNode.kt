package com.badoo.ribs.template.leaf_view_only.foo_bar

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.template.leaf_view_only.foo_bar.FooBar.Input
import com.badoo.ribs.template.leaf_view_only.foo_bar.FooBar.Output
import com.badoo.ribs.rx2.workflows.RxWorkflowNode

class FooBarNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<FooBarView>?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) : RxWorkflowNode<FooBarView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), FooBar, Connectable<Input, Output> by connector {

}
