package com.badoo.ribs.example.rib.switcher.feature

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.badoo.ribs.core.LifecycleParent
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.Portal
import com.badoo.ribs.core.Portal.Sink.Command
import com.badoo.ribs.core.Portal.Sink.Command.Add
import com.badoo.ribs.core.Portal.Sink.Command.Remove
import com.badoo.ribs.core.Portal.Source.Model
import com.badoo.ribs.core.Portal.Source.Model.Showing
import com.badoo.ribs.example.rib.switcher.feature.PortalFeature.Effect
import com.badoo.ribs.example.rib.switcher.feature.PortalFeature.Effect.Added
import com.badoo.ribs.example.rib.switcher.feature.PortalFeature.Effect.Removed
import com.badoo.ribs.example.rib.switcher.feature.PortalFeature.State
import io.reactivex.Observable
import io.reactivex.Observable.empty
import io.reactivex.Observable.just
import io.reactivex.ObservableSource
import javax.inject.Provider

// FIXME move somewhere else
class PortalFeature(
    lifecycleParent: Provider<out LifecycleParent>
): ActorReducerFeature<Command, Effect, State, Nothing>(
    initialState = State(),
    actor = ActorImpl(lifecycleParent),
    reducer = ReducerImpl()
), Portal.Sink, Portal.Source {

    data class State(
        val nodes: List<Node<*>> = emptyList()
    )

    sealed class Effect {
        data class Added(val node: Node<*>) : Effect()
        data class Removed(val node: Node<*>) : Effect()
    }

    class ActorImpl(
        private val lifecycleParent: Provider<out LifecycleParent>
    ) : Actor<State, Command, Effect> {
        override fun invoke(state: State, command: Command): Observable<out Effect> = when (command) {
            is Add -> if (state.nodes.contains(command.node)) empty() else
                just(Added(command.node)).doOnNext {
                    state.nodes.lastOrNull()?.let {
                        it.detachFromView()
                        lifecycleParent.get().removeParented(it)
                    }
                    lifecycleParent.get().addParented(it.node)
                }

            is Remove -> if (!state.nodes.contains(command.node)) empty() else
                just(Removed(command.node)).doOnNext {
                    it.node.detachFromView()
                    lifecycleParent.get().removeParented(it.node)
                }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is Added -> state.copy(nodes = state.nodes + effect.node)
            is Removed -> state.copy(nodes = state.nodes - effect.node)
        }
    }

    override val models: ObservableSource<Model>
        get() = Observable.wrap(this).map {
            when {
                it.nodes.isEmpty() -> Model.Empty
                else -> Showing(it.nodes.last())
            }
        }
}
