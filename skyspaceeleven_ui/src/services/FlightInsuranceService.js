import http from "../http-common"; 

class FlightInsuranceService {
  getAllFlightInsurances(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/flightInsurance/flightInsurances`, searchDTO);
  }

  get(flightInsuranceId) {
    return this.getRequest(`/flightInsurance/${flightInsuranceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/flightInsurance?field=${matchData}`, null);
  }

  addFlightInsurance(data) {
    return http.post("/flightInsurance/addFlightInsurance", data);
  }

  update(data) {
  	return http.post("/flightInsurance/updateFlightInsurance", data);
  }
  
  uploadImage(data,flightInsuranceId) {
  	return http.postForm("/flightInsurance/uploadImage/"+flightInsuranceId, data);
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

export default new FlightInsuranceService();
