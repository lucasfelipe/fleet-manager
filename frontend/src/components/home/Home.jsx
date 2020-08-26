import React, { Component } from 'react'
import Main from '../template/Main'
import MapContainer from '../../shared/map/Map'

const initialState = {
    places: [],
    truckNearbyPlaces: null
};

export default class HomeComponent extends Component {

    state = { ...initialState };

    render() {
        return (
            <Main>
                <MapContainer />
            </Main>
        )
    }

}