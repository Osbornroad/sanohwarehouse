<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div>
    <h2>${operation.id == null ? "Create a new operation" : "Update operation"}</h2>

    <sf:form action="/operations" method="post" modelAttribute="operation">
        <fieldset>
            <table cellspacing="0">
                <tr>
                    <sf:input type="hidden" path="id"/>
                </tr>
                <tr>
                    <th><label for="operation_name">Operation Name:</label></th>
                    <td><sf:input path="operationName" size="15" id="operation_name"/></td>
                </tr>
                <tr>
                    <th><label for="operation_sequence">Operation sequence:</label></th>
                    <td><sf:input path="operationSequence" size="15" id="operation_sequence"/></td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit"
                               value="Save" /></td>
                    <td><input name="reset" type="reset"
                               value="Reset" /></td>
                </tr>
            </table>
        </fieldset>
<%--        <button type="submit">Save</button>
        <button type="reset">Reset</button>--%>
    </sf:form>
</div>
<div>
    <sf:form action="/operations" method="get">
        <input type="submit" value="Operations">
    </sf:form>
</div>