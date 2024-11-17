import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Flights from  '@/pages/Flights.vue';
import FlightDetail from  '@/pages/FlightDetail.vue';
import Airports from  '@/pages/Airports.vue';
import AirportDetail from  '@/pages/AirportDetail.vue';
import Aircrafts from  '@/pages/Aircrafts.vue';
import AircraftDetail from  '@/pages/AircraftDetail.vue';
import Airlines from  '@/pages/Airlines.vue';
import AirlineDetail from  '@/pages/AirlineDetail.vue';
import Passengers from  '@/pages/Passengers.vue';
import PassengerDetail from  '@/pages/PassengerDetail.vue';
import Tickets from  '@/pages/Tickets.vue';
import TicketDetail from  '@/pages/TicketDetail.vue';
import Baggages from  '@/pages/Baggages.vue';
import BaggageDetail from  '@/pages/BaggageDetail.vue';
import BookingAgents from  '@/pages/BookingAgents.vue';
import BookingAgentDetail from  '@/pages/BookingAgentDetail.vue';
import Reservations from  '@/pages/Reservations.vue';
import ReservationDetail from  '@/pages/ReservationDetail.vue';
import Lounges from  '@/pages/Lounges.vue';
import LoungeDetail from  '@/pages/LoungeDetail.vue';
import Services from  '@/pages/Services.vue';
import ServiceDetail from  '@/pages/ServiceDetail.vue';
import LoyaltyPrograms from  '@/pages/LoyaltyPrograms.vue';
import LoyaltyProgramDetail from  '@/pages/LoyaltyProgramDetail.vue';
import Memberships from  '@/pages/Memberships.vue';
import MembershipDetail from  '@/pages/MembershipDetail.vue';
import Crews from  '@/pages/Crews.vue';
import CrewDetail from  '@/pages/CrewDetail.vue';
import FlightCrews from  '@/pages/FlightCrews.vue';
import FlightCrewDetail from  '@/pages/FlightCrewDetail.vue';
import Gates from  '@/pages/Gates.vue';
import GateDetail from  '@/pages/GateDetail.vue';
import ScheduledEvents from  '@/pages/ScheduledEvents.vue';
import ScheduledEventDetail from  '@/pages/ScheduledEventDetail.vue';
import Complaints from  '@/pages/Complaints.vue';
import ComplaintDetail from  '@/pages/ComplaintDetail.vue';
import CustomerServiceResponses from  '@/pages/CustomerServiceResponses.vue';
import CustomerServiceResponseDetail from  '@/pages/CustomerServiceResponseDetail.vue';
import Insurances from  '@/pages/Insurances.vue';
import InsuranceDetail from  '@/pages/InsuranceDetail.vue';
import FlightInsurances from  '@/pages/FlightInsurances.vue';
import FlightInsuranceDetail from  '@/pages/FlightInsuranceDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/flights',
																					  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/flights',
		name: 'Flights',
		layout: DefaultLayout,
		component: Flights,
	},
	{
	    path: '/flight/:flightId', 
	    name: 'FlightDetail',
		layout: DefaultLayout,
	    component: FlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airports',
		name: 'Airports',
		layout: DefaultLayout,
		component: Airports,
	},
	{
	    path: '/airport/:airportId', 
	    name: 'AirportDetail',
		layout: DefaultLayout,
	    component: AirportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/aircrafts',
		name: 'Aircrafts',
		layout: DefaultLayout,
		component: Aircrafts,
	},
	{
	    path: '/aircraft/:aircraftId', 
	    name: 'AircraftDetail',
		layout: DefaultLayout,
	    component: AircraftDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airlines',
		name: 'Airlines',
		layout: DefaultLayout,
		component: Airlines,
	},
	{
	    path: '/airline/:airlineId', 
	    name: 'AirlineDetail',
		layout: DefaultLayout,
	    component: AirlineDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passengers',
		name: 'Passengers',
		layout: DefaultLayout,
		component: Passengers,
	},
	{
	    path: '/passenger/:passengerId', 
	    name: 'PassengerDetail',
		layout: DefaultLayout,
	    component: PassengerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tickets',
		name: 'Tickets',
		layout: DefaultLayout,
		component: Tickets,
	},
	{
	    path: '/ticket/:ticketId', 
	    name: 'TicketDetail',
		layout: DefaultLayout,
	    component: TicketDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/baggages',
		name: 'Baggages',
		layout: DefaultLayout,
		component: Baggages,
	},
	{
	    path: '/baggage/:baggageId', 
	    name: 'BaggageDetail',
		layout: DefaultLayout,
	    component: BaggageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookingAgents',
		name: 'BookingAgents',
		layout: DefaultLayout,
		component: BookingAgents,
	},
	{
	    path: '/bookingAgent/:bookingAgentId', 
	    name: 'BookingAgentDetail',
		layout: DefaultLayout,
	    component: BookingAgentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reservations',
		name: 'Reservations',
		layout: DefaultLayout,
		component: Reservations,
	},
	{
	    path: '/reservation/:reservationId', 
	    name: 'ReservationDetail',
		layout: DefaultLayout,
	    component: ReservationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/lounges',
		name: 'Lounges',
		layout: DefaultLayout,
		component: Lounges,
	},
	{
	    path: '/lounge/:loungeId', 
	    name: 'LoungeDetail',
		layout: DefaultLayout,
	    component: LoungeDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/services',
		name: 'Services',
		layout: DefaultLayout,
		component: Services,
	},
	{
	    path: '/service/:serviceId', 
	    name: 'ServiceDetail',
		layout: DefaultLayout,
	    component: ServiceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/loyaltyPrograms',
		name: 'LoyaltyPrograms',
		layout: DefaultLayout,
		component: LoyaltyPrograms,
	},
	{
	    path: '/loyaltyProgram/:loyaltyProgramId', 
	    name: 'LoyaltyProgramDetail',
		layout: DefaultLayout,
	    component: LoyaltyProgramDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/memberships',
		name: 'Memberships',
		layout: DefaultLayout,
		component: Memberships,
	},
	{
	    path: '/membership/:membershipId', 
	    name: 'MembershipDetail',
		layout: DefaultLayout,
	    component: MembershipDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crews',
		name: 'Crews',
		layout: DefaultLayout,
		component: Crews,
	},
	{
	    path: '/crew/:crewId', 
	    name: 'CrewDetail',
		layout: DefaultLayout,
	    component: CrewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/flightCrews',
		name: 'FlightCrews',
		layout: DefaultLayout,
		component: FlightCrews,
	},
	{
	    path: '/flightCrew/:flightCrewId', 
	    name: 'FlightCrewDetail',
		layout: DefaultLayout,
	    component: FlightCrewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/gates',
		name: 'Gates',
		layout: DefaultLayout,
		component: Gates,
	},
	{
	    path: '/gate/:gateId', 
	    name: 'GateDetail',
		layout: DefaultLayout,
	    component: GateDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/scheduledEvents',
		name: 'ScheduledEvents',
		layout: DefaultLayout,
		component: ScheduledEvents,
	},
	{
	    path: '/scheduledEvent/:scheduledEventId', 
	    name: 'ScheduledEventDetail',
		layout: DefaultLayout,
	    component: ScheduledEventDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/complaints',
		name: 'Complaints',
		layout: DefaultLayout,
		component: Complaints,
	},
	{
	    path: '/complaint/:complaintId', 
	    name: 'ComplaintDetail',
		layout: DefaultLayout,
	    component: ComplaintDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/customerServiceResponses',
		name: 'CustomerServiceResponses',
		layout: DefaultLayout,
		component: CustomerServiceResponses,
	},
	{
	    path: '/customerServiceResponse/:customerServiceResponseId', 
	    name: 'CustomerServiceResponseDetail',
		layout: DefaultLayout,
	    component: CustomerServiceResponseDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/insurances',
		name: 'Insurances',
		layout: DefaultLayout,
		component: Insurances,
	},
	{
	    path: '/insurance/:insuranceId', 
	    name: 'InsuranceDetail',
		layout: DefaultLayout,
	    component: InsuranceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/flightInsurances',
		name: 'FlightInsurances',
		layout: DefaultLayout,
		component: FlightInsurances,
	},
	{
	    path: '/flightInsurance/:flightInsuranceId', 
	    name: 'FlightInsuranceDetail',
		layout: DefaultLayout,
	    component: FlightInsuranceDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
