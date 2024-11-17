import http from "../http-common"; 

class CustomerServiceResponseService {
  getAllCustomerServiceResponses(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/customerServiceResponse/customerServiceResponses`, searchDTO);
  }

  get(customerServiceResponseId) {
    return this.getRequest(`/customerServiceResponse/${customerServiceResponseId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/customerServiceResponse?field=${matchData}`, null);
  }

  addCustomerServiceResponse(data) {
    return http.post("/customerServiceResponse/addCustomerServiceResponse", data);
  }

  update(data) {
  	return http.post("/customerServiceResponse/updateCustomerServiceResponse", data);
  }
  
  uploadImage(data,customerServiceResponseId) {
  	return http.postForm("/customerServiceResponse/uploadImage/"+customerServiceResponseId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new CustomerServiceResponseService();
