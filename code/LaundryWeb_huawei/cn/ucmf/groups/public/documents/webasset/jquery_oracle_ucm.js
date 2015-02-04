/**
 * Oracle UCM Plugin for jQuery. Used to execute a AJAX call to UCM to get back
 * a JSON response. Can be used to process any request -- including content check-ins --
 * provided all the data can be bundled in the POST.
 */
(function($) {
	$.ucm = {

		/***********************************************************************/
		/* UCM Configuration                                                   */
		/***********************************************************************/

		/**
		 * the path to the content server web plugin
		 */
		cgiPath : "/idc/idcplg",

		/***********************************************************************/
		/* UCM Service Calls                                                   */
		/***********************************************************************/

		/**
		 * Execute any arbitrary UCM service, and then calls the optional callback function.
		 * The callback function has the signature 'function(ucmResponse)', where
		 * 'ucmResponse' is the JSON based response data
		 */
		executeService : function(ucmData, callback) {
			ucmData.IsJson = "1";
			$.post($.ucm.cgiPath, ucmData, function(responseHtml, textStatus, xmlHttp) {
					eval("var ucmResponse = " + responseHtml);
					$.ucm.checkForError(ucmResponse);
					ucmResponse.getValue = $.ucm.getValue;
					ucmResponse.getRow = $.ucm.getRow;
					ucmResponse.findRowIndex = $.ucm.findRowIndex;
					if (callback) {
						callback.call(this, ucmResponse);
					}
				},
			"html");
		},

		/**
		 * ping the content server to check for a connection, and alert upon successful connection
		 */
		pingServer : function(callback) {
			var pingData = {
				IdcService: "PING_SERVER"
			}
			$.ucm.executeService(pingData, function(ucmResponse) {
				alert(ucmResponse.getValue("StatusMessage"));
			});
		},


		/**
		 * use the "UCM Schema View" and "UCM Schema Relations" to download option
		 * lists from UCM. You can get a standard option list by leaving the 2nd and 3rd
		 * values blank (example: pass in 'docTypes' as the 'view'). You can also get
		 * dependent choice lists (example: set 'view' to 'LmLayoutSkinPairs', the
		 * relation to 'LmLayoutToSkin', and the 'parentValue' to 'Trays')
		 */
		getSchemaValues : function(view, relation, parentValue, callback) {
			var schemaData = {
				IdcService: "GET_SCHEMA_VIEW_FRAGMENT",
				schViewName:  view
			}
			if (relation) 
				schemaData["schRelationName"] = relation;
			if (parentValue) 
				schemaData["schParentValue"] = parentValue;
				
			$.ucm.executeService(schemaData, callback);
		},
		

		/**
		 * perform a quick search for 20 items
		 */
		quickSearch : function(quickSearchText, callback) {
			var queryTextData = {
				IdcService: "GET_SEARCH_RESULTS",
				QueryText : "<qsch>" + quickSearchText + "</qsch>",
				ResultCount : "20",
				SortField : "dInDate",
				SortOrder : "Desc",
				StartRow: "1"
			}
			$.ucm.executeService(queryTextData, callback);
		},

		 
		/**
		 * get the content information about an item
		 */
		docInfoByName : function(docName, callback) {
			var docInfoData = {
				IdcService: "DOC_INFO_BY_NAME",
				dDocName : docName
			}
			$.ucm.executeService(docInfoData, callback);
		},


		/**
		 * perform a check-out of a content item according to its dDocName
		 */
		checkOutByName : function(docName, callback) {
			var checkOutData = {
				IdcService: "CHECKOUT_BY_NAME",
				dDocName : docName
			}
			$.ucm.executeService(checkOutData, callback);
		},


		/**
		 * undo a check-out of a content item
		 */
		undoCheckOut : function(id, callback) {
			var undoCheckOutData = {
				IdcService: "UNDO_CHECKOUT",
				dID: id
			}
			$.ucm.executeService(undoCheckOutData, callback);
		},

		
		/**
		 * get information about the current user
		 */
		getUserInfo : function(callback) {
			var userInfoData = {
				IdcService: "GET_USER_INFO",
				IsJson : "1"
			}
			$.ucm.executeService(userInfoData, callback);
		},

		
		/**
		 * make a clone of the metadata of an existing item, and check in a new content
		 * item. All metadata will be cloned, except whatever is passed in 'checkInParams'
		 */
		checkInNewItemByName : function(checkInParams, primaryFileString, callback) {
			checkInParams.IdcService = "CHECKIN_NEW_ITEM_BY_NAME";
			checkInParams.doGeneratePrimaryFile = "1";
			checkInParams.primaryFileString = primaryFileString;
			$.ucm.executeService(userInfoData, callback);
		},
		

		/**
		 * check in a new revision of an existing content item. All metadata will be cloned, 
		 * except whatever is passed in 'checkInParams'
		 */
		checkInSelItemByName : function(checkInParams, primaryFileString, callback) {
			checkInParams.IdcService = "CHECKIN_SEL_ITEM_BY_NAME";
			checkInParams.doGeneratePrimaryFile = "1";
			checkInParams.primaryFileString = primaryFileString;
			$.ucm.executeService(checkInParams, callback);
		},

		
		/**
		 * execute the 'CHECKIN_SEL_FORM' service to pre-fill the metadat for
		 * the next revision of this item, including revision label
		 */
		checkInSelForm : function(id, callback) {
			var checkInFormData = {
				IdcService: "CHECKIN_SEL_FORM",
				dID : id
			}
			$.ucm.executeService(checkInFormData, callback);
		},
		
		
		/**
		 * make a clone of an item, then launch contributor for the new item
		 */
		cloneThenEditDataFile : function(docName) {
			var cloneData = {
				IdcService: "CHECKIN_NEW_ITEM_BY_NAME",
				dDocName : docName,
				IsJson : "1"
			}
			$.ucm.executeService(cloneData, function(ucmResponse) {
				var newDocName = getValue(ucmResponse, "dDocName");
				var editUrl = $.ucm.cgiPath + "?IdcService=WCM_BEGIN_EDIT_SESSION&dDocName=" + newDocName;
				window.open(editUrl, "_blank");
			});
		},


		/**
		 * Make a clone of a content item according to it's "region." The item must be a data file, and it
		 * must have a region template associated to it. The "clonable" content item must have a content id
		 * that corresponds to the region definition content id. In this case, to make a clone of an item
		 * with a region definition of "RGDEF_FOO", it will clone the content item "START_RGDEF_FOO"
		 */
		cloneRegion : function(placeholderName, dataFileDocName, nodeId, webSiteSection, isPrimary, callback) {
			var ucmResponse = null;
			var infoData = {
				IdcService: "DOC_INFO_BY_NAME",
				dDocName : dataFileDocName,
				nodeId : nodeId,
				isPrimary : isPrimary,
				placeholderName : placeholderName,
				webSiteSection : webSiteSection
			}
			$.ucm.executeService(infoData, function(ucmResponse) {
				var regionDefinition = ucmResponse.getValue("xRegionDefinition");
				var itemToClone = "START_" + regionDefinition;
				var cloneData = {
					IdcService: "CHECKIN_NEW_ITEM_BY_NAME",
					dDocName : itemToClone,
					nodeId : ucmResponse.getValue("nodeId"),
					isPrimary : ucmResponse.getValue("isPrimary"),
					placeholderName : ucmResponse.getValue("placeholderName"),
					dataFileDocName : ucmResponse.getValue("dDocName"),
					xWebsiteSection : ucmResponse.getValue("webSiteSection")
				}
				$.ucm.executeService(cloneData, function(ucmResponse) {
					callback.call(this, ucmResponse);
				});
			});
		},


		/***********************************************************************/
		/* UCM Utilities                                                       */
		/***********************************************************************/
		
		
		/**
		 * Check the response for a UCM error. If found, alert the user.
		 */
		checkForError : function(ucmResponse, suppressAlert) {
			var isError = false;
			if (typeof ucmResponse.LocalData.StatusCode != "undefined") {
				var statusCode = Number(ucmResponse.LocalData.StatusCode);
				if (statusCode < 0) {
					isError = true;
					var error = "Unknown UCM error.";
					if (typeof ucmResponse.LocalData.StatusMessage != "undefined") {
						error = ucmResponse.LocalData.StatusMessage;
					}
					if (!suppressAlert) {
						alert(error);
					}
				}
			}
			return isError;
		},


		/**
		 * iterate through the JSON based UCM response to find the value. It will
		 * check the LocalData first, then iterate through all ResultSets to find it
		 */
		getValue : function (key) {
			var value = null;
			if (this.LocalData != null && this.LocalData[key] != null) {
				value = this.LocalData[key];
			}
			else if (this.ResultSets != null) {
				var foundIt = false;
				for (var rsetName in this.ResultSets) {
					var rset = this.ResultSets[rsetName];
					var fieldIndex = 0;
					for ( ; fieldIndex < rset.fields.length; fieldIndex++) {
						var field = rset.fields[fieldIndex];
						if (field.name == key) {
							foundIt = true;
							break;
						}
					}
					if (foundIt) {
						if (rset.rows) {
							value = rset.rows[0][fieldIndex];
							break;
						}
					}
				}
			}
			return value;
		},
				
		/**
		 * extract a row from a result set as an associative array (hashtable)
		 */
		getRow : function(rset, row) {
			var props = new Array();
			if (this != null && this["ResultSets"] != null && row >= 0) {
				if (!rset.fields || !rset.rows) {
					rset = this["ResultSets"][rset];
				}
				var fields = rset.fields;
				for (var i=0; i<fields.length; i++) {
					var key = fields[i]["name"];
					var value = rset.rows[row][i];
					props[key] = value;
				}
			}
			return props;
		},
		

		/**
		 * iterate through a result set to find the row where the specific field has the specific value 
		 */
		findRowIndex : function(rsetName, fieldName, fieldValue) {
			var rowIndex = -1;
			if (this != null && this["ResultSets"] != null && this["ResultSets"][rsetName] != null) {
				var fieldIndex = -1;
				var fields = this["ResultSets"][rsetName].fields;
				for (var i=0; i<fields.length; i++) {
					var thisFieldName = fields[i]["name"];
					if (fieldName == thisFieldName) {
						fieldIndex = i;
						break;
					}
				}
				var rows = this["ResultSets"][rsetName].rows;
				for (var j=0; j<rows.length; j++) {
					if (rows[j][fieldIndex] == fieldValue) {
						rowIndex = j;
						break;
					}
				}
			}
			return rowIndex;
		},
		
		
		/**
		 * do an XML encoding of a parameter for an XML string (project file, data file) 
		 */
		xmlencode : function (string) {
			if (typeof string != "undefined" && string.length > 0) {
				string = string.replace(/\&/g,'&'+'amp;').replace(/</g,'&'+'lt;')
				.replace(/>/g,'&'+'gt;').replace(/\'/g,'&'+'apos;').replace(/\"/g,'&'+'quot;');
			}
			return string;
		}
	}
})(jQuery);
