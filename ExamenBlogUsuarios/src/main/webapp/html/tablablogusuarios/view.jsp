<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />

<portlet:resourceURL var="urlUsuarios"/>

<div id="miTablaDeUsuarios"></div>

<script type="text/javascript">

//Tabla aui

	YUI().ready('aui-datatable', 'aui-io-request',
			
	function(Y) {
		
		var columns = ['nombre', 'apellido', 'alias', 'email'];

		Y.io.request('<%=urlUsuarios%>',	
				{
			        on: {
			          success: function() {
			            var data = this.get('responseData');
			            
			            data=JSON.parse(data);
			            
			    		new Y.DataTable.Base({
			    			columnset : columns,
			    			recordset : data.list
			    		}).render('#miTablaDeUsuarios');
			          }
			        }
			      }
			    );
			}
	);
</script>