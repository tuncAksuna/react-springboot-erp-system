import React, { useEffect, useState } from 'react'
import EmployeeService from '../services/EmployeeService';
import { Link } from 'react-router-dom';

const ListEmployee = () => {

    const [employees, setEmployee] = useState([]);

    useEffect(() => {
        EmployeeService.getAllEmployees()
            .then((resp) => {
                setEmployee(resp.data);
            })
            .catch(err => {
                alert(err);
            })
    }, [])

    const getAllEmployees = () => {
        EmployeeService.getAllEmployees()
            .then((resp) => {
                setEmployee(resp.data);
            })
            .catch(err => {
                alert(err);
            })
    }

    const deleteEmployee = (employeeId) => {
        EmployeeService.deleteEmployee(employeeId)
            .then(resp => {
                getAllEmployees();
            })
            .catch(err => {
                alert(err);
            })
    }

    return (
        <div className="container">
            <h2 className='text-center'>Employee List</h2>
            <Link to="/add-employee" className='btn btn-primary btn-sm'>ADD EMPLOYEE</Link>
            <table className='table table-bordered table-striped'>
                <thead>
                    <tr>
                        <th>#Id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>E-mail ID</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        employees.map(employee =>
                            <tr key={employee.id}>
                                <td>{employee.id}</td>
                                <td>{employee.firstName}</td>
                                <td>{employee.lastName}</td>
                                <td>{employee.emailId}</td>
                                <td>
                                    <Link to={`/update-employee/${employee.id}`} className='btn btn-success btn-sm'>Update</Link>
                                    <button style={{ marginLeft: "10px" }} className="btn btn-danger btn-sm" onClick={() => deleteEmployee(employee.id)}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div >
    )
}

export default ListEmployee;
