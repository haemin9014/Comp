import React, { Component } from 'react'
import Slide from './Slide'
import LeftArrow from './LeftArrow'
import RightArrow from './RightArrow'

export class Slidshow extends Component{
  constructor(props) {
    super(props)
    //array of images
    this.state = {
      images: [
        "https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Fsnake.png?v=1576725395513",
        "https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2FSpace-invaders-game-preview..png?v=1576713911799",
        "https://cdn.glitch.com/d9c9199f-96e8-49b7-957a-282b4597a023%2Fpac.png?v=1576716625453",
      ],
      index: 0,
      change: 0
    }
  }
  // automove = () => {
  //   setInterval(() => {

  //     if(this.state.index >= this.state.images.length - 1) {
  //       return this.setState({
  //         index: 0,
  //         change: 0
  //       })
  //     }
  //     else if(this.state.index <= this.state.images.length - 1){
  //       this.setState(previous => ({
  //         index: previous.index + 1,
  //         change: previous.change - (this.slideWidth())
  //       }));
  //     }
  //   },5000);
  // }

  // code for going slide back
  goback = () => {
    if(this.state.index === 0){
      return;
    }
  
    // setState() enqueues changes to the component state 
    // and tells React that this component and its children 
    // need to be re-rendered with the updated state.  

  this.setState(previous => ({
    index: previous.index - 1,
    change: previous.change + this.slideWidth()
  }))  
  }
  
  // code for going next slide
  gonext = () => {
    if(this.state.index === this.state.images.length - 1) {
      return this.setState({
        index: 0,
        change: 0
      })
    }
   
    this.setState(previous => ({
      index: previous.index + 1,
      change: previous.change - (this.slideWidth())
    }));
  }

  // clientWidth property in javascript. 
  // clientWidth property is used to find the inner width of an element.
  // If the element doesnt contain any CSS or inline layout boxes, it returns zero. 
  // This property calculates the width as, CSS width + CSS padding - height of vertical scroll bar.
slideWidth = () => {
    return document.querySelector('.slide').clientWidth
 }
  
  render() {
    // this.automove();
    return (
      // loading array of image to use as a slider
      <div className="slider">

        <div className="slider-wrapper" style={{transform: `translateX(${this.state.change}px)`}}>
            {
              this.state.images.map((image, i) => (
                <Slide key={i} image={image} />
              ))
            }
        </div>
        {/* left arrow button, when we click it will go back */}
        <LeftArrow 
         goback={this.goback}
        />
        {/* right arrow button, when we click it will go next */}
        <RightArrow 
         gonext={this.gonext}    
        /> 
      </div>         
    );
  }
}


export default Slidshow