<html>
  <head>
	<meta name="showOrder-user" content="current" />
	<meta name="order" content="current" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="user" />
    <title>${licenseInstance.type} license for ${orderInstance.serviceId}</title>
  </head>
  <body>
	<h2>${licenseInstance.type} License</h2>
  <g:if test="${flash.message}">
      <div class="notification ${flash.code} png_bg">
          <div>
              ${flash.message}
          </div>
      </div>
  </g:if>
	<div id="message"></div> <!-- End .clear -->
	<div class="clear"></div> <!-- End .clear -->
    <div class="content-box">
	  
	  <div class="content-box-header">
		
		<h3>License overview</h3>
		<div class="clear"></div>
		
	  </div> <!-- End .content-box-header -->
	  
	  <div class="content-box-content">
		<table>
		  <tr>
			<td><strong>License type</strong></td>
			<td>${licenseInstance.type}</td>
		  </tr>
		  <tr>
			<td><strong>Valid until</strong></td>
			<td>${orderInstance.due}</td>
		  </tr>
		  <tr>
			<td><strong>License IP</strong></td>
			<td>${orderInstance.serviceId} <a class="button" rel="modal" href="#updateIp" >Update</a></td>
		  </tr>
		</table>
	  </div>
    </div>

			<div class="clear"></div> <!-- End .clear -->

  <div id="updateIp" style="display: none">
      <h3>Update License IP</h3>
      <g:form action="updateIp">
          <fieldset>
              <g:hiddenField name="id" value="${orderInstance.id}" />
              <p>
                  <label>New License IP:</label>
                  <g:textField name="ip"  class="text-input small-input" value="" />
              </p>
              <p>
                  <g:submitButton name="submit" class="button" value="Submit" />
              </p>
          </fieldset>
      </g:form>
  </div>

    
  </body>
</html>
