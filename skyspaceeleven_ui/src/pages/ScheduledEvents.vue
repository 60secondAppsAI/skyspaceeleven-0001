<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <scheduledEvent-table
            v-if="scheduledEvents && scheduledEvents.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:scheduledEvents="scheduledEvents"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-scheduled-events="getAllScheduledEvents"
             >

            </scheduledEvent-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ScheduledEventTable from "@/components/ScheduledEventTable";
import ScheduledEventService from "../services/ScheduledEventService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ScheduledEventTable,
  },
  data() {
    return {
      scheduledEvents: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllScheduledEvents(sortBy='scheduledEventId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ScheduledEventService.getAllScheduledEvents(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.scheduledEvents.length) {
					this.scheduledEvents = response.data.scheduledEvents;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching scheduledEvents:", error);
        }
        
      } catch (error) {
        console.error("Error fetching scheduledEvent details:", error);
      }
    },
  },
  mounted() {
    this.getAllScheduledEvents();
  },
  created() {
    this.$root.$on('searchQueryForScheduledEventsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllScheduledEvents();
    })
  }
};
</script>
<style></style>
