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

import com.glipka.easyReactJS.react._
import org.scalajs.dom._
import scalajs.js
import scalajs.js.{ Any => JAny }
import scalajs.js.{ Dynamic => jDynamic }
import scalajs.js.Dynamic._
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.JSConverters._
import com.glipka.easyReactJS.reactRouter.ReactRouter._

import com.glipka.easyReactJS.react.xml.XmlToCreatElement
//import reactBootstrap.ReactBootstrap._
import com.glipka.easyReactJS.reactBootstrap._
import com.glipka.easyReactJS.reactRouterBootstrap._
//import reactRouterBootstrap.ReactRouterBootstrap._
import scala.scalajs.js.UndefOr
import org.scalajs.dom.raw.HTMLInputElement
import org.scalajs.dom.raw.Event
import com.glipka.easyReactJS.redux.Store
import com.glipka.easyReactJS.redux.Redux._
import com.glipka.easyReactJS.reduxForm._
import com.glipka.easyReactJS.reduxForm.ReduxForm._
import scala.scalajs.js.JSConverters._
import examples.basicRouter.store._
import examples.basicRouter.redux.newActions._
import examples.basicRouter.redux.newActions.Actions._
import scala.scalajs.js.annotation.JSName
import examples.basicRouter.store._
 
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

@ScalaJSDefined
class Values extends js.Object {
  var clubName: String = ""
  var members: Fields = _
}
@ScalaJSDefined
object ValidateFieldArrays extends js.Object {

  @ScalaJSDefined
  class Errors extends js.Object {
    var clubName = ""
    var members: js.Array[js.Dynamic] = new js.Array[js.Dynamic]()

  }

  val validate: js.Function = (values: js.Dynamic) => {

    val errors = new Errors()
    var membersArrayErrors = new js.Array[js.Dynamic]()
    ////   if (values.isInstanceOf[Values]) { println("le parametre est une instance de values ") } else { println("le parametre n'est pas une instance de values") }
    //  if (values.isInstanceOf[Values]) {
    try {
      if (js.isUndefined(values) || js.isUndefined(values.clubName)) {
        errors.clubName = "Required"
      }

      if ((js.isUndefined(values) || js.isUndefined(values.members)) || js.isUndefined(values.members.length) || (values.members.length == 0)) {
        errors.members += literal("_error" -> "At least one member must be entered")
      } else {
        values.members.forEach((member: js.Dynamic, memberIndex: Int) => {
          var memberErrors: js.Dynamic = literal()
          if (js.isUndefined(member) || member.firstName.trim == "") {
            memberErrors = literal("firstName" -> "Required")
            membersArrayErrors(memberIndex) = memberErrors
          }
          if (js.isUndefined(member ) || member.lastName.trim == "") {
            memberErrors = literal("lastName" -> "Required")
            membersArrayErrors(memberIndex) = memberErrors
          }
          if (!js.isUndefined(member.hobbies)) {
            var hobbyArrayErrors = new js.Array[js.Any]()
            val hobbies = member.hobbies.asInstanceOf[Fields]
            var hobbyNonVide = 0
            hobbies.forEach((hobby: String, hobbyIndex: Int) => {
              if (js.isUndefined(hobby) || hobby == "") {
                hobbyArrayErrors(hobbyIndex) = "Required"
              } else {
                hobbyNonVide += 1
              }
              //      hobbyIndex+=1
            })
            if (hobbyArrayErrors.length > 0) {
              memberErrors.hobbies = hobbyArrayErrors
              membersArrayErrors(memberIndex) = memberErrors
            }
            if (hobbyNonVide > 5) {
              memberErrors.hobbies = literal("_error" -> "No more than five hobbies allowed")
              membersArrayErrors(memberIndex) = memberErrors
            }
          }
        })

      }
      if (membersArrayErrors.length > 0) {
        errors.members = membersArrayErrors
      }
    } catch {

      case ex: Exception => println("validate Field Array Erreur : " + ex.getMessage)
    }

    errors
  }
}

@ScalaJSDefined
class JSStateFieldArrays(var page: Int) extends js.Object

@ScalaJSDefined
class FieldArraysForm(var props: ReduxFormProps, context: js.Any) extends Component[ReduxFormProps, js.Dynamic](props, context) {
  val colors = js.Array("Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet")
  var globalIndex = 0
  var saveProps: FieldArrayProp = _

  @XmlToCreatElement(true)
  val renderField: js.Function = (props: js.Dynamic) =>
    <div>
      <label>{ props.label }</label>
      <div>
        <input type={ props.`type` } onFocus={ (evt: js.Any) => props.input.onFocus } onDrop={ (evt: js.Any) => props.input.onDrop(evt) } onDragStart={ (evt: js.Any) => props.input.onDragStart(evt) } onBlur={ (evt: js.Any) => props.input.onBlur(evt) } onChange={ (evt: js.Any) => props.input.onChange(evt) } name={ props.name } value={ props.input.value } placeholder={ props.input.name }/>
        <span> { props.meta.error }  </span>
      </div>
    </div>

  @XmlToCreatElement(true)
  val renderMembers: js.Function = (props1: FieldArrayProp) =>
    <ul>
      <li>
        <button type="button" onClick={ () => props1.fields.push(literal()) }>Add Member</button>
        { <span>{ props.error }</span> }
      </li>
      {
        props1.fields.map(
          (member: String, index: Int) =>
            <li key={ index }>
              <button type="button" title="Remove Member" onClick={ () => props1.fields.remove(index) }>Remove Member</button>
              <h4>Member   #{ index + 1 }</h4>
              <Field name={ member + ".firstName" } type="text" component={ renderField } label="First Name"/>
              <Field name={ member + ".lastName" } type="text" component={ renderField } label="Last Name"/>
              <FieldArray name={ member + ".hobbies" } component={ renderHobbies }/>
            </li>)
      }
    </ul>
  //   <FieldArray name="members[{index}].hobbies" component={renderHobbies}/>

  @XmlToCreatElement(true)
  val renderHobbies: js.Function = (props2: FieldArrayProp) =>
    <ul>
      { saveProps = props2 }
      <li>
        <button type="button" onClick={ () => props2.fields.push() }>Add Hobby</button>
      </li>
      {
        props2.fields.map(
          (hobby: String, index: Int) =>

            <li key={ index }>
              <button type="button" title="Remove Hobby" onClick={ () => props2.fields.remove(index) }> Remove Hobby </button>
              <Field name={ hobby } type="text" component={ renderField } label={ "Hobby #$ " + index }/>
            </li>)
      }
      { <li className="error">{ props2.meta.error }</li> }
    </ul>

  @XmlToCreatElement(true)
  override def render(): JAny = {
    <form onSubmit={ props.handleSubmit }>
      <Field name="clubName" type="text" component={ renderField } label="Club Name"/>
      <FieldArray name="members" component={ renderMembers }/>
      <div>
        <button type="submit" disabled={ props.submitting }>Submit</button>
        <button type="button" disabled={ props.pristine || props.submitting } onClick={ props.reset }>Clear Values</button>
      </div>
    </form>

  }

}

 

 

  