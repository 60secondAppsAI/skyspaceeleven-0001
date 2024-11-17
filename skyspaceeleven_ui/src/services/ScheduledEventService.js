import http from "../http-common"; 

class ScheduledEventService {
  getAllScheduledEvents(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/scheduledEvent/scheduledEvents`, searchDTO);
  }

  get(scheduledEventId) {
    return this.getRequest(`/scheduledEvent/${scheduledEventId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/scheduledEvent?field=${matchData}`, null);
  }

  addScheduledEvent(data) {
    return http.post("/scheduledEvent/addScheduledEvent", data);
  }

  update(data) {
  	return http.post("/scheduledEvent/updateScheduledEvent", data);
  }
  
  uploadImage(data,scheduledEventId) {
  	return http.postForm("/scheduledEvent/uploadImage/"+scheduledEventId, data);
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

export default new ScheduledEventService();
