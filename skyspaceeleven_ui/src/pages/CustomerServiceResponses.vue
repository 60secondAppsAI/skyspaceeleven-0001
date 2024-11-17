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
            <customerServiceResponse-table
            v-if="customerServiceResponses && customerServiceResponses.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:customerServiceResponses="customerServiceResponses"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-customer-service-responses="getAllCustomerServiceResponses"
             >

            </customerServiceResponse-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CustomerServiceResponseTable from "@/components/CustomerServiceResponseTable";
import CustomerServiceResponseService from "../services/CustomerServiceResponseService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CustomerServiceResponseTable,
  },
  data() {
    return {
      customerServiceResponses: [],
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
    async getAllCustomerServiceResponses(sortBy='customerServiceResponseId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CustomerServiceResponseService.getAllCustomerServiceResponses(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.customerServiceResponses.length) {
					this.customerServiceResponses = response.data.customerServiceResponses;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching customerServiceResponses:", error);
        }
        
      } catch (error) {
        console.error("Error fetching customerServiceResponse details:", error);
      }
    },
  },
  mounted() {
    this.getAllCustomerServiceResponses();
  },
  created() {
    this.$root.$on('searchQueryForCustomerServiceResponsesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCustomerServiceResponses();
    })
  }
};
</script>
<style></style>
