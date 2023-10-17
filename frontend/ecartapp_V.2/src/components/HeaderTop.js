import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';


export const HeaderTop = () => {
    return (
        <nav class="navbar navbar-light  header-background" >
            <div class="container items-center justify-between">
                <a class="navbar-brand" href="#">
                    <img src="assets/logo5.png"
                        height="40"
                        alt="Logo"
                        loading="lazy" />
                </a>
                <div className='headerTitle'>
                    Walmart - E - Cart
                </div>
                <div className='fontcolorW flex'>
                    <span>Welcome Guest &nbsp;</span> 
                    <img className='img_profile' src="assets/dp.jfif" id="output" width="200" />
                </div>
            </div>
        </nav>

    );
}
export default HeaderTop;