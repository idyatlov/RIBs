package com.badoo.ribs.test

@Suppress("FunctionNaming")
abstract class BaseNodeConnectorTest {

    abstract fun GIVEN_nodeConnector_onAttached_is_not_called_WHEN_output_is_accepted_THEN_accepted_output_do_not_reach_observer()

    abstract fun GIVEN_and_output_is_accepted_before_onAttached_WHEN_nodeConnector_onAttached_is_called_THEN_accepted_output_reach_the_observer()

    abstract fun GIVEN_nodeConnector_is_attached_WHEN_output_is_accepted_THEN_every_accepted_output_reach_the_observer()

    abstract fun GIVEN_outputs_accepted_before_and_after_onAttached_WHEN_node_is_attached_THEN_every_accepted_output_reach_the_observer()

    abstract fun WHEN_nodeConnector_onAttached_is_called_twice_THEN_error_is_raised()

    abstract fun GIVEN_multiple_observers_and_output_is_accepted_before_OnAttached_WHEN_nodeConnector_onAttached_is_called_THEN_every_accepted_output_reach_the_observers()

    abstract fun GIVEN_multiple_observers_and_nodeConnector_is_attached_WHEN_output_is_accepted_THEN_every_accepted_output_reach_the_observer()

    abstract fun GIVEN_multiple_observers_that_subscribe_before_and_after_onAttached__and_outputs_accepted_before_and_after_onAttached_WHEN_node_is_attached_THEN_every_accepted_output_reach_the_observer()

    abstract fun WHEN_multiple_output_are_accepted_from_multiple_threads_THEN_output_is_correctly_received_when_onAttached_is_called()

    abstract fun WHEN_accept_and_onAttached_are_called_by_different_thread_at_the_same_time_THEN_output_is_the_expected()
}
