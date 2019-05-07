
<!-- logon -->

    <form name="logonForm" action="/common/logon" method="post" target="_top" autocomplete="off">
    <fieldset class="fieldset" style="margin:15px"><legend>LOGON</legend>
        <table>
            <tr>
                <td align="right">Username</td>
                <td><input type="text" name="username" required="required" style="width:200px;" autocomplete="off" autofocus="autofocus"/></td>
                <td rowspan="4" valign="top">
                    <input id="sub" type="submit" value="Login &#187;" class="btn btn-green"/>
                </td>
            </tr>
            <tr>
                <td align="right">Password</td>
                <td><input type="password" name="password" required="required" style="width:200px;" autocomplete="off"/></td>
            </tr>
        </table>
    </fieldset>
    </form>