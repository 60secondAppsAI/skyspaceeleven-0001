
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Booking Agents</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalBookingAgents = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalBookingAgents">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add BookingAgent</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="BookingAgentId" type="text" placeholder="Enter BookingAgentId" v-model="bookingAgentToAdd.bookingAgentId"></base-input>
  <base-input label="Name" type="text" placeholder="Enter Name" v-model="bookingAgentToAdd.name"></base-input>
  <base-input label="AgencyName" type="text" placeholder="Enter AgencyName" v-model="bookingAgentToAdd.agencyName"></base-input>
  <base-input label="ContactEmail" type="text" placeholder="Enter ContactEmail" v-model="bookingAgentToAdd.contactEmail"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="bookingAgents" :row-key="record => record.BookingAgentId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <BookingAgentPictureView :bookingAgents="bookingAgents" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import BookingAgentService from "../services/BookingAgentService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import BookingAgentPictureView from './BookingAgentPictureView.vue';


const bookingAgentsColumns = [
  "bookingAgentId",
  "year",
  "date",
  "competitionId",
  "bookingAgentId"
]

export default {
  props: {
    bookingAgents: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    BookingAgentPictureView
  },

  data() {
    return {
      modalBookingAgents: false,
        isTableView: true,

      columns: [
        {
          title: 'Booking Agent Id',
		dataIndex: 'bookingAgentId',
          visible: true,
          scopedSlots: { customRender: 'bookingAgentId' },
          sorter: true
          //sorter: (a, b) => a.bookingAgentId - b.bookingAgentId,
          //sorter: (a, b) => a.bookingAgentId.localeCompare(b.bookingAgentId),
        },
        {
          title: 'Name',
		dataIndex: 'name',
          visible: true,
          scopedSlots: { customRender: 'name' },
          sorter: true
          //sorter: (a, b) => a.name - b.name,
          //sorter: (a, b) => a.name.localeCompare(b.name),
        },
        {
          title: 'Agency Name',
		dataIndex: 'agencyName',
          visible: true,
          scopedSlots: { customRender: 'agencyName' },
          sorter: true
          //sorter: (a, b) => a.agencyName - b.agencyName,
          //sorter: (a, b) => a.agencyName.localeCompare(b.agencyName),
        },
        {
          title: 'Contact Email',
		dataIndex: 'contactEmail',
          visible: true,
          scopedSlots: { customRender: 'contactEmail' },
          sorter: true
          //sorter: (a, b) => a.contactEmail - b.contactEmail,
          //sorter: (a, b) => a.contactEmail.localeCompare(b.contactEmail),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} bookingAgents`,
      },

      bookingAgents: [],
      bookingAgentToAdd: {},

      bookingAgentsTable: {
        columns: [...bookingAgentsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'bookingAgentId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderBookingAgentsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let bookingAgentsTableData = [];
      for (let i = 0; i < this.bookingAgents.length; i++) {
        bookingAgentsTableData.push({
          id: i,
          bookingAgentId: this.bookingAgents[i].bookingAgentId,
          year: this.bookingAgents[i].year,
          date: this.bookingAgents[i].date,
          competitionId: this.bookingAgents[i].competitionId,
          bookingAgentId: this.bookingAgents[i].bookingAgentId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-booking-agents',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToFlightDetail(id) {
      this.$router.push({ name: 'FlightDetail', params: { flightId: id.toString() }})
    },
    routingToAirportDetail(id) {
      this.$router.push({ name: 'AirportDetail', params: { airportId: id.toString() }})
    },
    routingToAircraftDetail(id) {
      this.$router.push({ name: 'AircraftDetail', params: { aircraftId: id.toString() }})
    },
    routingToAirlineDetail(id) {
      this.$router.push({ name: 'AirlineDetail', params: { airlineId: id.toString() }})
    },
    routingToPassengerDetail(id) {
      this.$router.push({ name: 'PassengerDetail', params: { passengerId: id.toString() }})
    },
    routingToTicketDetail(id) {
      this.$router.push({ name: 'TicketDetail', params: { ticketId: id.toString() }})
    },
    routingToBaggageDetail(id) {
      this.$router.push({ name: 'BaggageDetail', params: { baggageId: id.toString() }})
    },
    routingToBookingAgentDetail(id) {
      this.$router.push({ name: 'BookingAgentDetail', params: { bookingAgentId: id.toString() }})
    },
    routingToReservationDetail(id) {
      this.$router.push({ name: 'ReservationDetail', params: { reservationId: id.toString() }})
    },
    routingToLoungeDetail(id) {
      this.$router.push({ name: 'LoungeDetail', params: { loungeId: id.toString() }})
    },
    routingToServiceDetail(id) {
      this.$router.push({ name: 'ServiceDetail', params: { serviceId: id.toString() }})
    },
    routingToLoyaltyProgramDetail(id) {
      this.$router.push({ name: 'LoyaltyProgramDetail', params: { loyaltyProgramId: id.toString() }})
    },
    routingToMembershipDetail(id) {
      this.$router.push({ name: 'MembershipDetail', params: { membershipId: id.toString() }})
    },
    routingToCrewDetail(id) {
      this.$router.push({ name: 'CrewDetail', params: { crewId: id.toString() }})
    },
    routingToFlightCrewDetail(id) {
      this.$router.push({ name: 'FlightCrewDetail', params: { flightCrewId: id.toString() }})
    },
    routingToGateDetail(id) {
      this.$router.push({ name: 'GateDetail', params: { gateId: id.toString() }})
    },
    routingToScheduledEventDetail(id) {
      this.$router.push({ name: 'ScheduledEventDetail', params: { scheduledEventId: id.toString() }})
    },
    routingToComplaintDetail(id) {
      this.$router.push({ name: 'ComplaintDetail', params: { complaintId: id.toString() }})
    },
    routingToCustomerServiceResponseDetail(id) {
      this.$router.push({ name: 'CustomerServiceResponseDetail', params: { customerServiceResponseId: id.toString() }})
    },
    routingToInsuranceDetail(id) {
      this.$router.push({ name: 'InsuranceDetail', params: { insuranceId: id.toString() }})
    },
    routingToFlightInsuranceDetail(id) {
      this.$router.push({ name: 'FlightInsuranceDetail', params: { flightInsuranceId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForBookingAgentsChanged', this.searchQuery);
		//this.renderBookingAgentsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalBookingAgents = false;

      const currentDate = new Date().getTime();
      this.bookingAgentToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.bookingAgentToAdd);
      console.log(jsonData);
      
      const res = await BookingAgentService.addBookingAgent(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderBookingAgentsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
