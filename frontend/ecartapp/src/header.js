import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom';

export const PageHeader = () => {
    return (
        <>
            <Navbar bg="primary" data-bs-theme="dark">
                <Container>
                    <Navbar.Brand href="#home">
                        <img
                            src="Walmart Logo.png"  // Replace with your logo URL
                            width="100"
                            height="30"
                            className="d-inline-block align-top"
                            alt="Ecart.com Logo"
                        />
                        ECart.com
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="#home">Home</Nav.Link>
                            <NavDropdown title="Products" id="basic-nav-dropdown">
                                <NavDropdown.Item href="#action/3.1">Grocery</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.2">General Merchandise</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.3">Electronics</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.3">Clothing</NavDropdown.Item>
                                <NavDropdown.Item href="#action/3.3">
                                    <Link to="/phones">Phones</Link>
                                </NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item href="#action/3.4">Alcohol</NavDropdown.Item>
                            </NavDropdown>
                        </Nav>
                    </Navbar.Collapse>
                    <Navbar.Collapse className="justify-content-end">
                        <Form className="d-flex">
                            <Form.Control
                                type="search"
                                placeholder="Search"
                                className="me-2"
                                aria-label="Search"
                            />
                            <Button variant="outline-success">Search</Button>
                        </Form>
                        <Navbar.Text>
                            
                            {'\u00A0\u00A0\u00A0'}
                            <Button variant="info">
                            <Link to="/profile"> USER {/* Add a space */} </Link>
                            </Button>
                            {'\u00A0\u00A0\u00A0'}
                            <Button variant="info">
                                <Link to="/login">Login</Link>
                            </Button>
                        </Navbar.Text>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>

    );
}
