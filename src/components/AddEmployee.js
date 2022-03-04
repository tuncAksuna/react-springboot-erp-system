import React, { useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import EmployeeService from '../services/EmployeeService';


const AddEmployee = () => {

    const [firstName, setFirstname] = useState('');
    const [lastName, setLastName] = useState('');
    const [emailId, setEmailId] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        EmployeeService.getEmployeeById(id)
            .then(resp => {
                setFirstname(resp.data.firstName);
                setLastName(resp.data.lastName);
                setEmailId(resp.data.emailId);
            })
    }, []);


    const saveFirstName = (e) => {
        setFirstname(e.target.value);
    }

    const saveLastName = (e) => {
        setLastName(e.target.value);
    }

    const saveEmailId = (e) => {
        setEmailId(e.target.value);
    }

    const saveOrUpdateEmployee = (e) => {
        e.preventDefault();

        const employee = { firstName, lastName, emailId };

        if (id) {
            EmployeeService.updateEmployee(id, employee)
                .then(resp => {
                    navigate('/employees');
                })
                .catch(err => {
                    console.warn(err);
                })
        } else {
            EmployeeService.addEmployee(employee)
                .then(resp => {
                    navigate('/employees');
                })
                .catch(err => {
                    alert(err);
                });
        }
    }

    const changeTitle = () => {
        if (id) {
            return < h3 className="text-center">Update Employee</h3 >

        }
        return <h3 className="text-center">Add Employee</h3>
    }

    return (
        <>
            <div className="container">
                <div className="card col-md-10 offset-md-3 offset-md-3">
                    {
                        changeTitle()
                    }
                    <div className="card-body">
                        <form>
                            <div className="form-group mb-2">
                                <label htmlFor="firstName" className='form-label'>First Name</label>
                                <input type="text"
                                    placeholder="Enter first name"
                                    name="firstName"
                                    className='form-control'
                                    value={firstName}
                                    onChange={saveFirstName}
                                />
                            </div>
                            <div className="form-group mb-2">
                                <label htmlFor="emailId" className='form-label'>Last Name</label>
                                <input type="text"
                                    placeholder="Enter last name"
                                    name="emailId"
                                    className='form-control'
                                    value={lastName}
                                    onChange={saveLastName}
                                />
                            </div>
                            <div className="form-group mb-2">
                                <label htmlFor="emailId" className='form-label'>Email Id</label>
                                <input type="text"
                                    placeholder="Enter email id"
                                    name="emailId"
                                    className='form-control'
                                    value={emailId}
                                    onChange={saveEmailId}
                                />
                            </div>
                            {
                                firstName, lastName, emailId && <button className='btn btn-success' onClick={saveOrUpdateEmployee}>Save Employee</button>
                            }
                            <Link to="/employees" className='btn btn-danger'>Cancel</Link>
                        </form>
                    </div>
                </div>
            </div>

        </>
    )
}

export default AddEmployee;