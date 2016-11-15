/*
 # Copyright 2016 Georges Lipka
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
*/
package examples.basicRouter.redux.components

import react._
import org.scalajs.dom._
import scalajs.js
import scalajs.js._
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.JSConverters._
import reactRouter.ReactRouter._

import react.xml.XmlToCreatElement
import reactBootstrap.ReactBootstrap._
import reactBootstrap._
import reactRouterBootstrap._
import reactRouterBootstrap.ReactRouterBootstrap._
import scala.scalajs.js.UndefOr
import org.scalajs.dom.raw.HTMLInputElement
import org.scalajs.dom.raw.Event
import redux.Store
import redux.Redux._

import scala.scalajs.js.JSConverters._
import examples.basicRouter.store._
import examples.basicRouter.redux.newActions._
import examples.basicRouter.redux.newActions.Actions._

@js.native
trait JSPropsFriendInput extends js.Object {
  def key: UndefOr[String] = js.native
  def ref: UndefOr[js.Function] = js.native
  def children: PropsChildren = js.native
 
  val addFriend : js.Function1[String,Any]=js.native
  

}

@ScalaJSDefined
class JSStateFriendInput extends js.Object {
  var name = ""

}

@ScalaJSDefined
class AddFriendInput(val props: JSPropsFriendInput) extends Component[JSPropsFriendInput, JSStateFriendInput](props) {
   val name1: String=""
  
  override def componentWillMount: Unit = {
    setState(new JSStateFriendInput{ // on renseigne le stat 
       name=name1
    })
    
  }
  override def componentDidMount: Unit = {
    

  }
  override def componentWillReceiveProps(nextProps: JSPropsFriendInput, nextContext: js.Any) {}
  
  val handleChange:js.Function =(e:Event ) => {
   
     setState(new JSStateFriendInput{ // on renseigne le stat 
        name = e.target.valueOf().asInstanceOf[HTMLInputElement].value
        println("method hangleChange : contenu du champ name: " + name)
  
    })
  }
     

 val handleSubmit :js.Function =(e:Event ) => {
   val endOfline ="\13"
     val value = e.target.valueOf().asInstanceOf[HTMLInputElement].value.trim
     
     // e.which
    if (value.endsWith("$")) {
      this.props.addFriend(value);
       setState(new JSStateFriendInput{  
        name = "" 
         })}
}
   
  @XmlToCreatElement(true)
  override def render(): Any = {
    
     <input
        type="text"
        autoFocus="true"
         
        placeholder="Type the name of a friend"
        value={this.state.name}
        onChange={this.handleChange.bind(this)}
				onKeyDown={this.handleSubmit.bind(this)} />
    
    
     
  }

} 

 