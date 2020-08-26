import { Map, InfoWindow, Marker, GoogleApiWrapper } from 'google-maps-react';
import React, { Component } from 'react';

export class MapContainer extends Component {
    render() {
        return (
            <Map google={this.props.google} zoom={14}>
                <Marker onClick={this.onMarkerClick}
                        name={'Current location'} />
 
            
            </Map>
        )
    }
}

const LoadingContainer = (props) => <div>Loading!</div>

export default GoogleApiWrapper({
    apiKey: '',
    LoadingContainer: LoadingContainer
})(MapContainer)
