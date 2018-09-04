<!DOCTYPE html>
<html>
<head>
<#assign title = "流程编辑" />
<#include "/common/head.ftl">
<script type="text/javascript">
    RESOURCES_PATH="/mxgraph/grapheditor/resources"
    STYLE_PATH="/mxgraph/grapheditor/styles"
</script>
<script type="text/javascript">
		// Parses URL parameters. Supported parameters are:
		// - lang=xy: Specifies the language of the user interface.
		// - touch=1: Enables a touch-style user interface.
		// - storage=local: Enables HTML5 local storage.
		// - chrome=0: Chromeless mode.
		var urlParams = (function(url)
		{
			var result = new Object();
			var idx = url.lastIndexOf('?');
	
			if (idx > 0)
			{
				var params = url.substring(idx + 1).split('&');
				
				for (var i = 0; i < params.length; i++)
				{
					idx = params[i].indexOf('=');
					
					if (idx > 0)
					{
						result[params[i].substring(0, idx)] = params[i].substring(idx + 1);
					}
				}
			}
			
			return result;
		})(window.location.href);
	
		// Default resources are included in grapheditor resources
		mxLoadResources = false;
</script>
<link rel="stylesheet" type="text/css" href="${basePath}mxgraph/grapheditor/styles/grapheditor.css" />
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/js/Init.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/deflate/pako.min.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/deflate/base64.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/jscolor/jscolor.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/sanitizer/sanitizer.min.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/js/mxClient.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/js/EditorUi.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/js/editor.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/js/Sidebar.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/js/Graph.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/js/Format.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/js/Shapes.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/js/Actions.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/js/Menus.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/js/Toolbar.js"></script>
<script type="text/JavaScript" src="${basePath}mxgraph/grapheditor/js/Dialogs.js"></script>
</head>
<body class="geEditor">
<script type="text/javascript">
		// Extends EditorUi to update I/O action states based on availability of backend
		(function()
		{
			var editorUiInit = EditorUi.prototype.init;
			
			EditorUi.prototype.init = function()
			{
				editorUiInit.apply(this, arguments);
				this.actions.get('export').setEnabled(false);

				// Updates action states which require a backend
				if (!Editor.useLocalStorage)
				{
					mxUtils.post(OPEN_URL, '', mxUtils.bind(this, function(req)
					{
						var enabled = req.getStatus() != 404;
						this.actions.get('open').setEnabled(enabled || Graph.fileSupport);
						this.actions.get('import').setEnabled(enabled || Graph.fileSupport);
						this.actions.get('save').setEnabled(enabled);
						this.actions.get('saveAs').setEnabled(enabled);
						this.actions.get('export').setEnabled(enabled);
					}));
				}
			};
			
			// Adds required resources (disables loading of fallback properties, this can only
			// be used if we know that all keys are defined in the language specific file)
			mxResources.loadDefaultBundle = false;
			var bundle = mxResources.getDefaultBundle(RESOURCE_BASE, mxLanguage) ||
				mxResources.getSpecialBundle(RESOURCE_BASE, mxLanguage);

			// Fixes possible asynchronous requests
			mxUtils.getAll([bundle, STYLE_PATH + '/default.xml'], function(xhr)
			{
				// Adds bundle text to resources
				mxResources.parse(xhr[0].getText());
				
				// Configures the default graph theme
				var themes = new Object();
				themes[Graph.prototype.defaultThemeName] = xhr[1].getDocumentElement(); 
				
				// Main
				new EditorUi(new Editor(urlParams['chrome'] == '0', themes));
			}, function()
			{
				document.body.innerHTML = '<center style="margin-top:10%;">Error loading resource files. Please check browser console.</center>';
			});
		})();
</script>
</body>
</html>