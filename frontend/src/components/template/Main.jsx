import './Main.css'
import React from 'react'

export default props =>
    <main className="content container-fluid">
        <div>
            {props.children}
        </div>
    </main>