<template>
  <a-layout style="min-height: 100vh">
    <!-- Header -->
    <a-layout-header style="background: #fff; padding: 0">
      <app-header />
    </a-layout-header>

    <!-- Main layout with sider and content -->
    <a-layout>
      <!-- Collapsible Sider with Controlled Open Keys for Nested Menu -->
      <a-layout-sider width="200px" collapsible style="background: #fff">
        <a-menu
          mode="inline"
          :openKeys="openKeys"
          @openChange="onOpenChange"
          style="height: 100%; border-right: 0"
        >
<!--          <a-sub-menu key="sub1" title="Menu 1">
            <a-menu-item key="1">Option 1</a-menu-item>
            <a-menu-item key="2">Option 2</a-menu-item>
            <a-sub-menu key="sub1-1" title="Nested Menu">
              <a-menu-item key="3">Option 3</a-menu-item>
              <a-menu-item key="4">Option 4</a-menu-item>
            </a-sub-menu>
          </a-sub-menu>
          <a-sub-menu key="sub2" title="Menu 2">
            <a-menu-item key="5">Option 5</a-menu-item>
            <a-menu-item key="6">Option 6</a-menu-item>
          </a-sub-menu>
-->>

	 
			
				<a-menu-item>
					<router-link to="/flights">
						<span class="label">Flights</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/airports">
						<span class="label">Airports</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/aircrafts">
						<span class="label">Aircrafts</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/airlines">
						<span class="label">Airlines</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/passengers">
						<span class="label">Passengers</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/tickets">
						<span class="label">Tickets</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/baggages">
						<span class="label">Baggages</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/bookingAgents">
						<span class="label">Booking Agents</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/reservations">
						<span class="label">Reservations</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/lounges">
						<span class="label">Lounges</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/services">
						<span class="label">Services</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/loyaltyPrograms">
						<span class="label">Loyalty Programs</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/memberships">
						<span class="label">Memberships</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/crews">
						<span class="label">Crews</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/flightCrews">
						<span class="label">Flight Crews</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/gates">
						<span class="label">Gates</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/scheduledEvents">
						<span class="label">Scheduled Events</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/complaints">
						<span class="label">Complaints</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/customerServiceResponses">
						<span class="label">Customer Service Responses</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/insurances">
						<span class="label">Insurances</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/flightInsurances">
						<span class="label">Flight Insurances</span>
					</router-link>
				</a-menu-item>
		
	
        </a-menu>
      </a-layout-sider>
      
      <!-- Content area -->
      <a-layout-content style="padding: 16px">
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import { ref } from "vue";
import AppHeader from "@/components/AppHeader.vue";

export default {
  name: "DefaultLayout",
  components: {
    AppHeader,
  },
  setup() {
    const openKeys = ref(["sub1"]); // default open submenu
    const onOpenChange = (keys) => {
      // Open only the last clicked submenu, for single open behavior
      openKeys.value = keys.length ? [keys[keys.length - 1]] : [];
    };

    return {
      openKeys,
      onOpenChange,
    };
  },
};
</script>
<style>
.ant-layout {
    margin-top: 20px;
}
</style>

