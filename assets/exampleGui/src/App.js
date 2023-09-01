import React, {Component} from 'react'
import FurhatGUI from 'furhat-gui'
import { Grid, Row, Col } from 'react-bootstrap'
import Button from './Button'
import Input from './Input'

class App extends Component {

    constructor(props) {
        super(props)
        this.state = {
          "imageName" : "assets/img/50JahrePHV.jpg"
        }
        this.furhat = null
    }

    setupSubscriptions() {
        // Our DataDelivery event is getting no custom name and hence gets it's full class name as event name.
        this.furhat.subscribe('furhatos.app.furgui.DataDelivery', (data) => {
            this.setState({
                ...this.state,
                imageName: "assets/img/" + data.imageName
            })
        })
    }

    componentDidMount() {
        FurhatGUI()
            .then(connection => {
                this.furhat = connection
                this.setupSubscriptions()
            })
            .catch(console.error)
    }

    render() {
      return (
                <div>
                    <h1>Dialysezentrum</h1>
                    <h2>{this.state.imageName}</h2>
                        <img src={this.state.imageName}/>
                </div>
        )
    }

}

export default App;
