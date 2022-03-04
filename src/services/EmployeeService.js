import axios from "axios";


const BASE_ENDPOINT = "http://localhost:8080/api/v1";


class EmployeeService {

    getAllEmployees() {
        return axios.get(`${BASE_ENDPOINT}/employees`);
    }

    addEmployee(employee) {
        return axios.post(`${BASE_ENDPOINT}/addEmployee`, employee);
    }

    getEmployeeById(employeeId) {
        return axios.get(`${BASE_ENDPOINT}/employees/${employeeId}`);
    }

    updateEmployee(employeeId, employeeDetails) {
        return axios.put(`${BASE_ENDPOINT}/update/${employeeId}`, employeeDetails);
    }

    deleteEmployee(employeeId) {
        return axios.delete(`${BASE_ENDPOINT}/delete/${employeeId}`);
    }

}


export default new EmployeeService();