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
            <flightInsurance-table
            v-if="flightInsurances && flightInsurances.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:flightInsurances="flightInsurances"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-flight-insurances="getAllFlightInsurances"
             >

            </flightInsurance-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import FlightInsuranceTable from "@/components/FlightInsuranceTable";
import FlightInsuranceService from "../services/FlightInsuranceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    FlightInsuranceTable,
  },
  data() {
    return {
      flightInsurances: [],
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
    async getAllFlightInsurances(sortBy='flightInsuranceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await FlightInsuranceService.getAllFlightInsurances(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.flightInsurances.length) {
					this.flightInsurances = response.data.flightInsurances;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching flightInsurances:", error);
        }
        
      } catch (error) {
        console.error("Error fetching flightInsurance details:", error);
      }
    },
  },
  mounted() {
    this.getAllFlightInsurances();
  },
  created() {
    this.$root.$on('searchQueryForFlightInsurancesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllFlightInsurances();
    })
  }
};
</script>
<style></style>
