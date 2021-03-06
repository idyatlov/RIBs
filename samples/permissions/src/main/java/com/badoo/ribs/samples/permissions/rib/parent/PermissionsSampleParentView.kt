package com.badoo.ribs.samples.permissions.rib.parent

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.badoo.ribs.samples.permissions.R
import com.badoo.ribs.samples.permissions.rib.child1.PermissionsSampleChild1
import com.badoo.ribs.samples.permissions.rib.child2.PermissionsSampleChild2

interface PermissionsSampleParentView : RibView {

    interface Factory : ViewFactoryBuilder<Nothing?, PermissionsSampleParentView>
}

class PermissionsSampleParentViewImpl private constructor(
    override val androidView: ViewGroup
) : AndroidRibView(), PermissionsSampleParentView {

    private val child1Container: ViewGroup = androidView.findViewById(R.id.child1_container)
    private val child2Container: ViewGroup = androidView.findViewById(R.id.child2_container)

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_permissions_sample_parent
    ) : PermissionsSampleParentView.Factory {
        override fun invoke(deps: Nothing?): ViewFactory<PermissionsSampleParentView> = ViewFactory {
            PermissionsSampleParentViewImpl(
                it.inflate(layoutRes)
            )
        }
    }

    override fun getParentViewForSubtree(subtreeOf: Node<*>): ViewGroup =
        when (subtreeOf) {
            is PermissionsSampleChild1 -> child1Container
            is PermissionsSampleChild2 -> child2Container
            else -> super.getParentViewForSubtree(subtreeOf)
        }
}
