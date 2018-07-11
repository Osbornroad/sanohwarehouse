<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div>
    <h2>Create a new contact</h2>

    <sf:form method="post" modelAttribute="operation">
        <fieldset>
            <table cellspacing="0">
                <tr>
                    <th><label for="operation_name">Operation Name:</label></th>
                    <td><sf:input path="operationName" size="15" id="operation_name"/></td>
                </tr>
                <tr>
                    <th><label for="operation_sequence">First Name:</label></th>
                    <td><sf:input path="operationSequence" size="15" id="operation_sequence"/></td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit"
                               value="Create" /></td>
                </tr>
            </table>
        </fieldset>
    </sf:form>
</div>