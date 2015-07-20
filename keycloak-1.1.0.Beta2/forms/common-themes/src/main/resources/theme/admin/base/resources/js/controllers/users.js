module.controller('UserRoleMappingCtrl', function($scope, $http, realm, user, applications, Notifications, RealmRoleMapping,
                                                  ApplicationRoleMapping, AvailableRealmRoleMapping, AvailableApplicationRoleMapping,
                                                  CompositeRealmRoleMapping, CompositeApplicationRoleMapping, AvailableApplicationModules,
                                                  AvailableModuleRoleMapping, ModuleRoleMapping, CompositeModuleRoleMapping) { 
    $scope.realm = realm;
    $scope.user = user;
    $scope.selectedRealmRoles = [];
    $scope.selectedRealmMappings = [];
    $scope.realmMappings = [];
    $scope.applications = applications;
    $scope.applicationRoles = [];
    $scope.applicationComposite = [];
    $scope.selectedApplicationRoles = [];
    $scope.selectedApplicationMappings = [];
    $scope.applicationMappings = [];
    $scope.dummymodel = [];
    
    // 2015-05-06
    $scope.modules = [];
    $scope.moduleRoles = [];
    $scope.moduleComposite = [];
    $scope.moduleMappings = [];
    $scope.selectedModuleRoles = [];
    $scope.selectedModuleMappings = [];

    $scope.realmMappings = RealmRoleMapping.query({realm : realm.realm, userId : user.username});
    $scope.realmRoles = AvailableRealmRoleMapping.query({realm : realm.realm, userId : user.username});
    $scope.realmComposite = CompositeRealmRoleMapping.query({realm : realm.realm, userId : user.username});

    $scope.addRealmRole = function() {
        $http.post(authUrl + '/admin/realms/' + realm.realm + '/users/' + user.username + '/role-mappings/realm',
                $scope.selectedRealmRoles).success(function() {
                $scope.realmMappings = RealmRoleMapping.query({realm : realm.realm, userId : user.username});
                $scope.realmRoles = AvailableRealmRoleMapping.query({realm : realm.realm, userId : user.username});
                $scope.realmComposite = CompositeRealmRoleMapping.query({realm : realm.realm, userId : user.username});
                $scope.selectedRealmMappings = [];
                $scope.selectRealmRoles = [];
                if ($scope.application) {
                    console.log('load available');
                    $scope.applicationComposite = CompositeApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                    $scope.applicationRoles = AvailableApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                    $scope.applicationMappings = ApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                    $scope.selectedApplicationRoles = [];
                    $scope.selectedApplicationMappings = [];
                }
                Notifications.success("Role mappings updated.");

            });
    };

    $scope.deleteRealmRole = function() {
        $http.delete(authUrl + '/admin/realms/' + realm.realm + '/users/' + user.username + '/role-mappings/realm',
            {data : $scope.selectedRealmMappings, headers : {"content-type" : "application/json"}}).success(function() {
                $scope.realmMappings = RealmRoleMapping.query({realm : realm.realm, userId : user.username});
                $scope.realmRoles = AvailableRealmRoleMapping.query({realm : realm.realm, userId : user.username});
                $scope.realmComposite = CompositeRealmRoleMapping.query({realm : realm.realm, userId : user.username});
                $scope.selectedRealmMappings = [];
                $scope.selectRealmRoles = [];
                if ($scope.application) {
                    console.log('load available');
                    $scope.applicationComposite = CompositeApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                    $scope.applicationRoles = AvailableApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                    $scope.applicationMappings = ApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                    $scope.selectedApplicationRoles = [];
                    $scope.selectedApplicationMappings = [];
                }
                Notifications.success("Role mappings updated.");
            });
    };

    $scope.addApplicationRole = function() {
        $http.post(authUrl + '/admin/realms/' + realm.realm + '/users/' + user.username + '/role-mappings/applications-by-id/' + $scope.application.id,
                $scope.selectedApplicationRoles).success(function() {
                $scope.applicationMappings = ApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                $scope.applicationRoles = AvailableApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                $scope.applicationComposite = CompositeApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                if ($scope.module) {
                	$scope.moduleRoles = AvailableModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
                }
                
                $scope.selectedApplicationRoles = [];
                $scope.selectedApplicationMappings = [];
                Notifications.success("Role mappings updated.");
            });
    };

    $scope.deleteApplicationRole = function() {
        $http.delete(authUrl + '/admin/realms/' + realm.realm + '/users/' + user.username + '/role-mappings/applications-by-id/' + $scope.application.id,
            {data : $scope.selectedApplicationMappings, headers : {"content-type" : "application/json"}}).success(function() {
                $scope.applicationMappings = ApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                $scope.applicationRoles = AvailableApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                $scope.applicationComposite = CompositeApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
                if ($scope.module) {
                	$scope.moduleRoles = AvailableModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
                }
                $scope.selectedApplicationRoles = [];
                $scope.selectedApplicationMappings = [];
                Notifications.success("Role mappings updated.");
            });
    };


    $scope.changeApplication = function() {
        console.log('changeApplication');
        if ($scope.application) {
            console.log('load available');
            $scope.applicationComposite = CompositeApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
            $scope.applicationRoles = AvailableApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
            $scope.applicationMappings = ApplicationRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id});
            $scope.modules = AvailableApplicationModules.query({realm : realm.realm, application : $scope.application.id});
            $scope.module = null;
        } else {
            $scope.applicationRoles = null;
            $scope.applicationMappings = null;
            $scope.applicationComposite = null;
            $scope.modules = null;
        }
        $scope.selectedApplicationRoles = [];
        $scope.selectedApplicationMappings = [];
        $scope.selectedModuleMappings = [];
        $scope.selectedModuleRoles = [];
        
        $scope.moduleRoles = null;
        $scope.moduleComposite = null;
        $scope.moduleMappings = null;
    };

    // ================================================================
    // ==========================MODULE ROLES==========================
    // ================================================================
    
    $scope.addModuleRole = function() {
    	console.log('addModuleRole');
    	$http.post(authUrl + '/admin/realms/' + realm.realm + '/users/' + user.username + '/role-mappings/applications-by-id/' + $scope.application.id + "/modules/" + $scope.module.id,
                $scope.selectedModuleRoles).success(function() {
                $scope.moduleMappings = ModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
                $scope.moduleRoles = AvailableModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
                $scope.moduleComposite = CompositeModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
                $scope.moduleRoles = AvailableModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
                $scope.selectedModuleRoles = [];
                $scope.selectedModuleMappings = [];
                Notifications.success("Role mappings updated.");
            });
    };
    
    $scope.deleteModuleRole = function() {
    	console.log('deleteModuleRole');
    	$http.delete(authUrl + '/admin/realms/' + realm.realm + '/users/' + user.username + '/role-mappings/applications-by-id/' + $scope.application.id + "/modules/" + $scope.module.id,
    			{data : $scope.selectedModuleMappings, headers : {"content-type" : "application/json"}}).success(function() {
                $scope.moduleMappings = ModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
                $scope.moduleRoles = AvailableModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
                $scope.moduleComposite = CompositeModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
                $scope.moduleRoles = AvailableModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
                $scope.selectedModuleRoles = [];
                $scope.selectedModuleMappings = [];
                Notifications.success("Role mappings updated.");
            });
    };
    
    $scope.changeModule = function() {
    	console.log('changeModule');
    	if ($scope.module) {
    		$scope.moduleMappings = ModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
    	    $scope.moduleRoles = AvailableModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
            $scope.moduleComposite = CompositeModuleRoleMapping.query({realm : realm.realm, userId : user.username, application : $scope.application.id, module : $scope.module.id});
    	} else {
            $scope.moduleRoles = null;
            $scope.moduleMappings = null;
            $scope.moduleComposite = null;
            $scope.modules = null;
        }
        $scope.selectedModulenRoles = [];
        $scope.selectedModuleMappings = [];
    };
    
});

module.controller('UserSessionsCtrl', function($scope, realm, user, sessions, UserSessions, UserLogout, UserSessionLogout, Notifications) {
    $scope.realm = realm;
    $scope.user = user;
    $scope.sessions = sessions;

    $scope.logoutAll = function() {
        UserLogout.save({realm : realm.realm, user: user.username}, function () {
            Notifications.success('Logged out user in all applications');
            UserSessions.query({realm: realm.realm, user: user.username}, function(updated) {
                $scope.sessions = updated;
            })
        });
    };

    $scope.logoutSession = function(sessionId) {
        console.log('here in logoutSession');
        UserSessionLogout.delete({realm : realm.realm, session: sessionId}, function() {
            UserSessions.query({realm: realm.realm, user: user.username}, function(updated) {
                $scope.sessions = updated;
                Notifications.success('Logged out session');
            })
        });
    }
});

module.controller('UserSocialCtrl', function($scope, realm, user, socialLinks) {
    $scope.realm = realm;
    $scope.user = user;
    $scope.socialLinks = socialLinks;
    console.log('showing social links of user');
});


module.controller('UserListCtrl', function($scope, realm, User) {
    $scope.realm = realm;
    $scope.page = 0;

    $scope.query = {
        realm: realm.realm,
        max : 5,
        first : 0
    }

    $scope.firstPage = function() {
        $scope.query.first = 0;
        $scope.searchQuery();
    }

    $scope.previousPage = function() {
        $scope.query.first -= parseInt($scope.query.max);
        if ($scope.query.first < 0) {
            $scope.query.first = 0;
        }
        $scope.searchQuery();
    }

    $scope.nextPage = function() {
        $scope.query.first += parseInt($scope.query.max);
        $scope.searchQuery();
    }

    $scope.searchQuery = function() {
        console.log("query.search: " + $scope.query.search);
        $scope.searchLoaded = false;

        $scope.users = User.query($scope.query, function() {
            $scope.searchLoaded = true;
            $scope.lastSearch = $scope.query.search;
        });
    };
});



module.controller('UserDetailCtrl', function($scope, realm, user, User, userTypes, userSubTypes, UserFederationInstances, $location, Dialog, Notifications) {
    $scope.realm = realm;
    $scope.user = angular.copy(user);
    $scope.create = !user.username;
    // get userTypeList userSubTypeList from server.
    $scope.userTypeList = angular.copy(userTypes);

    // get userTypeList userSubTypeList from server.
    $scope.userSubTypes = angular.copy(userSubTypes);

    if ($scope.create) {
        $scope.userSubTypeList = angular.copy([]);
        $scope.user.enabled = true;
        $scope.user.customUserTypeId = "";
        $scope.user.customUserSubTypeId = "";
    } else {
        if(user.federationLink) {
            console.log("federationLink is not null");
            UserFederationInstances.get({realm : realm.realm, instance: user.federationLink}, function(link) {
                $scope.federationLinkName = link.displayName;
                $scope.federationLink = "#/realms/" + realm.realm + "/user-federation/providers/" + link.providerName + "/" + link.id;
            })
        } else {
            console.log("federationLink is null");
        }

        // init subtype dropdown
        var tmp =[];
        var array = $scope.userSubTypes;
        for (var i = 0; i < array.length; i++) {
            if(array[i].userType == $scope.user.customUserTypeId)
                tmp.push(array[i]);
        }
        $scope.userSubTypeList = tmp;
    }

    $scope.changed = false; // $scope.create;

    // ID - Name map for required actions. IDs are enum names.
    $scope.userReqActionList = [
        {id: "VERIFY_EMAIL", text: "Verify Email"},
        {id: "UPDATE_PROFILE", text: "Update Profile"},
        {id: "CONFIGURE_TOTP", text: "Configure Totp"},
        {id: "UPDATE_PASSWORD", text: "Update Password"}
    ];

    $scope.changeUserType = function() {
        var tmp =[];
        var array = $scope.userSubTypes;
        for (var i = 0; i < array.length; i++) {
            if(array[i].userType == $scope.user.customUserTypeId)
                tmp.push(array[i]);
        }
        $scope.userSubTypeList = tmp;
        $scope.user.customUserSubTypeId = "";
        document.getElementById('userSubType').removeAttribute('disabled');
    };

    $scope.$watch('user', function() {
        if (!angular.equals($scope.user, user)) {
            $scope.changed = true;
        }
    }, true);

    $scope.save = function() {
        if ($scope.create) {
            User.save({
                realm: realm.realm
            }, $scope.user, function () {
                $scope.changed = false;
                user = angular.copy($scope.user);

                $location.url("/realms/" + realm.realm + "/users/" + $scope.user.username);
                Notifications.success("The user has been created.");
            });
        } else {
            User.update({
                realm: realm.realm,
                userId: $scope.user.username
            }, $scope.user, function () {
                $scope.changed = false;
                user = angular.copy($scope.user);
                Notifications.success("Your changes have been saved to the user.");
            });
        }
    };

    $scope.reset = function() {
        $scope.user = angular.copy(user);
        $scope.changed = false;
    };

    $scope.cancel = function() {
        $location.url("/realms/" + realm.realm + "/users");
    };

    $scope.remove = function() {
        Dialog.confirmDelete($scope.user.username, 'user', function() {
            $scope.user.$remove({
                realm : realm.realm,
                userId : $scope.user.username
            }, function() {
                $location.url("/realms/" + realm.realm + "/users");
                Notifications.success("The user has been deleted.");
            }, function() {
                Notifications.error("User couldn't be deleted");
            });
        });
    };
});

module.controller('UserCredentialsCtrl', function($scope, realm, user, User, UserCredentials, Notifications, Dialog) {
    console.log('UserCredentialsCtrl');

    $scope.realm = realm;
    $scope.user = angular.copy(user);
    $scope.temporaryPassword = true;

    $scope.isTotp = false;
    if(!!user.totp){
        $scope.isTotp = user.totp;
    }

    $scope.resetPassword = function() {
        if ($scope.pwdChange) {
            if ($scope.password != $scope.confirmPassword) {
                Notifications.error("Password and confirmation does not match.");
                return;
            }
        }

        var msgTitle = 'Change password';
        var msg = 'Are you sure you want to change the users password?';

        Dialog.confirm(msgTitle, msg, function() {
            UserCredentials.resetPassword({ realm: realm.realm, userId: user.username }, { type : "password", value : $scope.password, temporary: $scope.temporaryPassword }, function() {
                Notifications.success("The password has been reset");
                $scope.password = null;
                $scope.confirmPassword = null;
            }, function() {
                Notifications.error("Failed to reset user password");
            });
        }, function() {
            $scope.password = null;
            $scope.confirmPassword = null;
        });
    };

    $scope.removeTotp = function() {
        Dialog.confirm('Remove totp', 'Are you sure you want to remove the users totp configuration?', function() {
            UserCredentials.removeTotp({ realm: realm.realm, userId: user.username }, { }, function() {
                Notifications.success("The users totp configuration has been removed");
                $scope.user.totp = false;
            }, function() {
                Notifications.error("Failed to remove the users totp configuration");
            });
        });
    };

    $scope.resetPasswordEmail = function() {
        Dialog.confirm('Reset password email', 'Are you sure you want to send password reset email to user?', function() {
            UserCredentials.resetPasswordEmail({ realm: realm.realm, userId: user.username }, { }, function() {
                Notifications.success("Password reset email sent to user");
            }, function() {
                Notifications.error("Failed to send password reset mail to user");
            });
        });
    };

    $scope.$watch('user', function() {
        if (!angular.equals($scope.user, user)) {
            $scope.userChange = true;
        } else {
            $scope.userChange = false;
        }
    }, true);

    $scope.$watch('password', function() {
        if (!!$scope.password){
            $scope.pwdChange = true;
        } else {
            $scope.pwdChange = false;
        }
    }, true);

    $scope.reset = function() {
        $scope.password = "";
        $scope.confirmPassword = "";

        $scope.user = angular.copy(user);

        $scope.isTotp = false;
        if(!!user.totp){
            $scope.isTotp = user.totp;
        }

        $scope.pwdChange = false;
        $scope.userChange = false;
    };
});

module.controller('UserFederationCtrl', function($scope, $location, realm, UserFederationProviders, UserFederationInstances, Notifications, Dialog) {
    console.log('UserFederationCtrl ++++****');
    $scope.realm = realm;
    $scope.providers = UserFederationProviders.query({realm: realm.realm});

    $scope.addProvider = function(provider) {
        console.log('Add provider: ' + provider.id);
        $location.url("/create/user-federation/" + realm.realm + "/providers/" + provider.id);
    };

    $scope.instances = UserFederationInstances.query({realm: realm.realm});

});

module.controller('GenericUserFederationCtrl', function($scope, $location, Notifications, Dialog, realm, instance, providerFactory, UserFederationInstances, UserFederationSync) {
    console.log('GenericUserFederationCtrl');

    $scope.create = !instance.providerName;
    $scope.providerFactory = providerFactory;

    console.log("providerFactory: " + providerFactory.id);

    function initFederationSettings() {
        if ($scope.create) {
            instance.providerName = providerFactory.id;
            instance.config = {};
            instance.priority = 0;
            $scope.fullSyncEnabled = false;
            $scope.changedSyncEnabled = false;
        } else {
            $scope.fullSyncEnabled = (instance.fullSyncPeriod && instance.fullSyncPeriod > 0);
            $scope.changedSyncEnabled = (instance.changedSyncPeriod && instance.changedSyncPeriod > 0);
        }

        $scope.changed = false;
    }

    initFederationSettings();
    $scope.instance = angular.copy(instance);
    $scope.realm = realm;

    $scope.$watch('fullSyncEnabled', function(newVal, oldVal) {
        if (oldVal == newVal) {
            return;
        }

        $scope.instance.fullSyncPeriod = $scope.fullSyncEnabled ? 604800 : -1;
        $scope.changed = true;
    });

    $scope.$watch('changedSyncEnabled', function(newVal, oldVal) {
        if (oldVal == newVal) {
            return;
        }

        $scope.instance.changedSyncPeriod = $scope.changedSyncEnabled ? 86400 : -1;
        $scope.changed = true;
    });

    $scope.$watch('instance', function() {
        if (!angular.equals($scope.instance, instance)) {
            $scope.changed = true;
        }

    }, true);

    $scope.save = function() {
        $scope.changed = false;
        if ($scope.create) {
            UserFederationInstances.save({realm: realm.realm}, $scope.instance,  function () {
                $scope.changed = false;
                $location.url("/realms/" + realm.realm + "/user-federation");
                Notifications.success("The provider has been created.");
            });
        } else {
            UserFederationInstances.update({realm: realm.realm,
                    instance: instance.id
                },
                $scope.instance,  function () {
                    $scope.changed = false;
                    $location.url("/realms/" + realm.realm + "/user-federation");
                    Notifications.success("The provider has been updated.");
                });

        }
    };

    $scope.reset = function() {
        initFederationSettings();
        $scope.instance = angular.copy(instance);
    };

    $scope.cancel = function() {
        $location.url("/realms/" + realm.realm + "/user-federation");
    };

    $scope.remove = function() {
        Dialog.confirm('Delete', 'Are you sure you want to permanently delete this provider?  All imported users will also be deleted.', function() {
            $scope.instance.$remove({
                realm : realm.realm,
                instance : $scope.instance.id
            }, function() {
                $location.url("/realms/" + realm.realm + "/user-federation");
                Notifications.success("The provider has been deleted.");
            });
        });
    };

    $scope.triggerFullSync = function() {
        console.log('GenericCtrl: triggerFullSync');
        triggerSync('triggerFullSync');
    }

    $scope.triggerChangedUsersSync = function() {
        console.log('GenericCtrl: triggerChangedUsersSync');
        triggerSync('triggerChangedUsersSync');
    }

    function triggerSync(action) {
        UserFederationSync.get({ action: action, realm: $scope.realm.realm, provider: $scope.instance.id }, function() {
            Notifications.success("Sync of users finished successfully");
        }, function() {
            Notifications.error("Error during sync of users");
        });
    }
});


module.controller('LDAPCtrl', function($scope, $location, Notifications, Dialog, realm, instance, UserFederationInstances, UserFederationSync, RealmLDAPConnectionTester) {
    console.log('LDAPCtrl');
    var DEFAULT_BATCH_SIZE = "1000";

    $scope.create = !instance.providerName;

    function initFederationSettings() {
        if ($scope.create) {
            instance.providerName = "ldap";
            instance.config = {};
            instance.priority = 0;
            $scope.syncRegistrations = false;

            $scope.userAccountControlsAfterPasswordUpdate = true;
            instance.config.userAccountControlsAfterPasswordUpdate = "true";

            $scope.connectionPooling = true;
            instance.config.connectionPooling = "true";

            $scope.pagination = true;
            instance.config.pagination = "true";
            instance.config.batchSizeForSync = DEFAULT_BATCH_SIZE;

            $scope.fullSyncEnabled = false;
            $scope.changedSyncEnabled = false;
        } else {
            $scope.syncRegistrations = instance.config.syncRegistrations && instance.config.syncRegistrations == "true";
            $scope.userAccountControlsAfterPasswordUpdate = instance.config.userAccountControlsAfterPasswordUpdate && instance.config.userAccountControlsAfterPasswordUpdate == "true";
            $scope.connectionPooling = instance.config.connectionPooling && instance.config.connectionPooling == "true";
            $scope.pagination = instance.config.pagination && instance.config.pagination == "true";
            if (!instance.config.batchSizeForSync) {
                instance.config.batchSizeForSync = DEFAULT_BATCH_SIZE;
            }
            $scope.fullSyncEnabled = (instance.fullSyncPeriod && instance.fullSyncPeriod > 0);
            $scope.changedSyncEnabled = (instance.changedSyncPeriod && instance.changedSyncPeriod > 0);
        }

        $scope.changed = false;
        $scope.lastVendor = instance.config.vendor;
    }

    initFederationSettings();
    $scope.instance = angular.copy(instance);

    $scope.ldapVendors = [
        { "id": "ad", "name": "Active Directory" },
        { "id": "rhds", "name": "Red Hat Directory Server" },
        { "id": "tivoli", "name": "Tivoli" },
        { "id": "other", "name": "Other" }
    ];

    $scope.usernameLDAPAttributes = [
        "uid", "cn", "sAMAccountName", "entryDN"
    ];

    $scope.realm = realm;

    function watchBooleanProperty(propertyName) {
        $scope.$watch(propertyName, function() {
            if ($scope[propertyName]) {
                $scope.instance.config[propertyName] = "true";
            } else {
                $scope.instance.config[propertyName] = "false";
            }
        })
    }

    watchBooleanProperty('syncRegistrations');
    watchBooleanProperty('userAccountControlsAfterPasswordUpdate');
    watchBooleanProperty('connectionPooling');
    watchBooleanProperty('pagination');

    $scope.$watch('fullSyncEnabled', function(newVal, oldVal) {
        if (oldVal == newVal) {
            return;
        }

        $scope.instance.fullSyncPeriod = $scope.fullSyncEnabled ? 604800 : -1;
        $scope.changed = true;
    });

    $scope.$watch('changedSyncEnabled', function(newVal, oldVal) {
        if (oldVal == newVal) {
            return;
        }

        $scope.instance.changedSyncPeriod = $scope.changedSyncEnabled ? 86400 : -1;
        $scope.changed = true;
    });

    $scope.$watch('instance', function() {
        if (!angular.equals($scope.instance, instance)) {
            $scope.changed = true;
        }

        if (!angular.equals($scope.instance.config.vendor, $scope.lastVendor)) {
            console.log("LDAP vendor changed");
            $scope.lastVendor = $scope.instance.config.vendor;

            if ($scope.lastVendor === "ad") {
                $scope.instance.config.usernameLDAPAttribute = "cn";
                $scope.instance.config.userObjectClasses = "person, organizationalPerson, user";
            } else {
                $scope.instance.config.usernameLDAPAttribute = "uid";
                $scope.instance.config.userObjectClasses = "inetOrgPerson, organizationalPerson";
            }
        }
    }, true);

    $scope.save = function() {
        $scope.changed = false;

        if (!parseInt($scope.instance.config.batchSizeForSync)) {
            $scope.instance.config.batchSizeForSync = DEFAULT_BATCH_SIZE;
        } else {
            $scope.instance.config.batchSizeForSync = parseInt($scope.instance.config.batchSizeForSync).toString();
        }

        if ($scope.create) {
            UserFederationInstances.save({realm: realm.realm}, $scope.instance,  function () {
                $scope.changed = false;
                $location.url("/realms/" + realm.realm + "/user-federation");
                Notifications.success("The provider has been created.");
            });
        } else {
            UserFederationInstances.update({realm: realm.realm,
                                          instance: instance.id
                },
                $scope.instance,  function () {
                $scope.changed = false;
                $location.url("/realms/" + realm.realm + "/user-federation");
                Notifications.success("The provider has been updated.");
            });

        }
    };

    $scope.reset = function() {
        initFederationSettings();
        $scope.instance = angular.copy(instance);
    };

    $scope.cancel = function() {
        $location.url("/realms/" + realm.realm + "/user-federation");
    };

    $scope.remove = function() {
        Dialog.confirm('Delete', 'Are you sure you want to permanently delete this provider?  All imported users will also be deleted.', function() {
            $scope.instance.$remove({
                realm : realm.realm,
                instance : $scope.instance.id
            }, function() {
                $location.url("/realms/" + realm.realm + "/user-federation");
                Notifications.success("The provider has been deleted.");
            });
        });
    };


    var initConnectionTest = function(testAction, ldapConfig) {
        return {
            action: testAction,
            realm: $scope.realm.realm,
            connectionUrl: ldapConfig.connectionUrl,
            bindDn: ldapConfig.bindDn,
            bindCredential: ldapConfig.bindCredential
        };
    };

    $scope.testConnection = function() {
        console.log('LDAPCtrl: testConnection');
        RealmLDAPConnectionTester.get(initConnectionTest("testConnection", $scope.instance.config), function() {
            Notifications.success("LDAP connection successful.");
        }, function() {
            Notifications.error("Error when trying to connect to LDAP. See server.log for details.");
        });
    }

    $scope.testAuthentication = function() {
        console.log('LDAPCtrl: testAuthentication');
        RealmLDAPConnectionTester.get(initConnectionTest("testAuthentication", $scope.instance.config), function() {
            Notifications.success("LDAP authentication successful.");
        }, function() {
            Notifications.error("LDAP authentication failed. See server.log for details");
        });
    }

    $scope.triggerFullSync = function() {
        console.log('LDAPCtrl: triggerFullSync');
        triggerSync('triggerFullSync');
    }

    $scope.triggerChangedUsersSync = function() {
        console.log('LDAPCtrl: triggerChangedUsersSync');
        triggerSync('triggerChangedUsersSync');
    }

    function triggerSync(action) {
        UserFederationSync.get({ action: action, realm: $scope.realm.realm, provider: $scope.instance.id }, function() {
            Notifications.success("Sync of users finished successfully");
        }, function() {
            Notifications.error("Error during sync of users");
        });
    }

});
/*Start add new by HieuDM*/  
module.controller('UserTypeListCtrl', function($scope, realm, UserType) {
    $scope.realm = realm;
    $scope.page = 0;

    $scope.query = {
        realm: realm.realm,
        max : 5,
        first : 0
    }

    $scope.firstPage = function() {
        $scope.query.first = 0;
        $scope.searchQuery();
    }

    $scope.previousPage = function() {
        $scope.query.first -= parseInt($scope.query.max);
        if ($scope.query.first < 0) {
            $scope.query.first = 0;
        }
        $scope.searchQuery();
    }

    $scope.nextPage = function() {
        $scope.query.first += parseInt($scope.query.max);
        $scope.searchQuery();
    }

    $scope.searchQuery = function() {
        console.log("query.search: " + $scope.query.search);
        $scope.searchLoaded = false;

        $scope.userTypes = UserType.query($scope.query, function() {
            $scope.searchLoaded = true;
            $scope.lastSearch = $scope.query.search;
        });
    };
});

//User type screen
module.controller('UserTypeDetailCtrl', function($scope, $upload, realm, userType, UserType, userTypeRoles, $location, Dialog, Notifications) {
	$scope.realm = realm;
    $scope.userType = angular.copy(userType);
    $scope.availableUserTypeRoleMappings = angular.copy(userTypeRoles);
    $scope.applicationUserTypeRoles=[];
    $scope.applicationComposite=[];
    $scope.create = !userType.name;
    $scope.files = [];
    $scope.changed = false; // $scope.create;
    $scope.fileName = 'TnC-' + userType.name + '.txt';
	
	
	if (!$scope.create  &&  $scope.userType.userTypeRole) {
		// TODO: Get all roles tag for module
		console.log('roleeeee: ' + $scope.userType.userTypeRole);
		var roleNames = angular.copy($scope.userType.userTypeRole);
        roleNames = roleNames.replace(/\"/g, '');
        roleNames = roleNames.substring(1,roleNames .length-1);
        roleNames = roleNames.split(",");
        
        for(var i = 0; i < roleNames.length; i++){
        	for(var j = 0; j < $scope.availableUserTypeRoleMappings.length; j++){
    			console.log("filter item name: "+ $scope.availableUserTypeRoleMappings[j].name);
    			if ($scope.availableUserTypeRoleMappings[j].name.trim() == roleNames[i].trim()){
    				$scope.applicationUserTypeRoles.push($scope.availableUserTypeRoleMappings[j]);
    				$scope.availableUserTypeRoleMappings.splice(j, 1);
    			}
    		}
        }
        
        $scope.applicationComposite = angular.copy($scope.applicationUserTypeRoles);
	} 
    
	$scope.$watch('availableUserTypeRoleMappings', function() {
		console.log("availableUserTypeRoleMappings: "+JSON.stringify($scope.availableUserTypeRoleMappings));
	});
    
	$scope.$watch('applicationUserTypeRoles', function() {
		console.log("applicationUserTypeRoles: "+JSON.stringify($scope.applicationUserTypeRoles));		
		if (!angular.equals($scope.applicationComposite, $scope.applicationUserTypeRoles)) {
            $scope.changed = true;
        }
	},true);
	 
    $scope.$watch('userType', function() {
        if (!angular.equals($scope.userType, userType)) {
            $scope.changed = true;
        }
    }, true);
	
	$scope.$watch('applicationComposite', function() {
		console.log("applicationComposite: "+JSON.stringify($scope.applicationComposite));
	});
	
	$scope.addUserTypeRole = function() {
		// TODO: add user type role
		if($scope.selectedUserTypeRoles.length == 0)
			return;
			
			for(var i = 0; i < $scope.selectedUserTypeRoles.length; i++){
				$scope.applicationUserTypeRoles.push($scope.selectedUserTypeRoles[i]);
				var selectedItem = $scope.selectedUserTypeRoles[i];
	    		for(var j = 0; j < $scope.availableUserTypeRoleMappings.length; j++){
	    			if ($scope.availableUserTypeRoleMappings[j].name.trim() == selectedItem.name.trim()){
	    				$scope.availableUserTypeRoleMappings.splice(j, 1);
	    			}
	    		}
			}
    		
    		$scope.selectedUserTypeRoles = [];
    };
    
    $scope.deleteUserTypeRole = function() {
    	if($scope.selectedApplicationUserTypeRoles.length == 0)
    		return;
    	
    	//if ($scope.create) {
    		for(var i = 0; i < $scope.selectedApplicationUserTypeRoles.length; i++){
    			for(var j = 0; j < $scope.applicationUserTypeRoles.length; j++){
	    			if ($scope.selectedApplicationUserTypeRoles[i].name.trim() === $scope.applicationUserTypeRoles[j].name.trim()){
	    				$scope.applicationUserTypeRoles.splice(j, 1);
	    				$scope.availableUserTypeRoleMappings.push($scope.selectedApplicationUserTypeRoles[i]);
	    			}
	    		}
    		}
    		$scope.selectedApplicationUserTypeRoles = [];
    };
    
    $scope.onFileSelect = function($files) {
        $scope.files = $files;
        $scope.changed = true;
    };

    $scope.clearFileSelect = function() {
        $scope.files = null;
        if (!angular.equals($scope.userType, userType)) {
            $scope.changed = true;
        }
    }

    $scope.uploadFile = function() {
    	var roleNames = new Array();
    	for(var i=0; i<$scope.applicationUserTypeRoles.length; i++){
    		roleNames.push($scope.applicationUserTypeRoles[i].name);
    	}
    	
    	$scope.userType.userTypeRole = roleNames;
        if ($scope.create) {
            var $file = $scope.files[0];
         
			if($scope.files.length != 1){
                Notifications.error("Please select a Tnc content file!");
            }
            $scope.upload = $upload.upload({
                url: authUrl + '/admin/realms/' + realm.realm + '/user-types/',
                data: {userTypeName: $scope.userType.name,
                    userTypeId: " ",
                    roleNames: roleNames
                },
                file: $file
            }).progress(function(evt) {
                console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
            }).success(function(data, status, headers) {
				
                Notifications.success("User type has been created successfully.");
                $location.url("/realms/" + realm.realm + "/user-types/" + $scope.userType.name);
            }).error(function() {
                Notifications.error("User type can not be created.");
            });
        } else if($scope.files != null && $scope.files.length > 0){
            var $file = $scope.files[0];

            $scope.upload = $upload.upload({
                url: authUrl + '/admin/realms/' + realm.realm + '/user-types/',
                data: {
                    userTypeId: $scope.userType.id,
                    userTypeName: $scope.userType.name,
                    roleNames: roleNames
                },
                file: $file
            }).progress(function(evt) {
                console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
            }).success(function(data, status, headers) {
            	Notifications.success("Your changes have been saved to the user type!");
            	$location.path("/realms/" + realm.realm + "/user-types/" + $scope.userType.name+"/");
            }).error(function() {
                Notifications.error("Error occurs when updating!");
            });
        } else {        	
        	var str = '';        	
        	for (var i = 0; i < $scope.userType.userTypeRole.length; i++) {
				str = str + $scope.userType.userTypeRole[i];
				if(i < $scope.userType.userTypeRole.length - 1)
				{
					str = str + '","';
				}
			}
        	
        	str = '["' + str + '"]';
        	console.log('new roles :' + str);
        	$scope.userType.userTypeRole = angular.copy(str);
            UserType.update({
                realm: realm.realm,
                userType: $scope.userType.id
            }, $scope.userType, function () {
                $scope.changed = false;
                userType = angular.copy($scope.userType);
                $scope.availableUserTypeRoleMappings = angular.copy(userTypeRoles);
                $scope.applicationUserTypeRoles = [];
                
                if ($scope.userType.userTypeRole) {

	                var roleNames = angular.copy($scope.userType.userTypeRole);
	                roleNames = roleNames.replace(/\"/g, '');
	                roleNames = roleNames.substring(1,roleNames .length-1);
	                roleNames = roleNames.split(",");
	                for(var i = 0; i < roleNames.length; i++){
	                	for(var j = 0; j < $scope.availableUserTypeRoleMappings.length; j++){
	            			if ($scope.availableUserTypeRoleMappings[j].name.trim() == roleNames[i].trim()){
	            				$scope.applicationUserTypeRoles.push($scope.availableUserTypeRoleMappings[j]);
	            				$scope.availableUserTypeRoleMappings.splice(j, 1);
	            			}
	            		}
	                }
                }
                $scope.applicationComposite = angular.copy($scope.applicationUserTypeRoles);
                Notifications.success("Your changes have been saved to the user type.");
            });
        }
    };

    $scope.download = function() {
        var blob = base64toBlob($scope.userType.tncContent);
        blobToFile(blob);
    }

    function base64toBlob(base64Data) {
        contentType = '';
        var sliceSize = 1024;
        var byteCharacters = atob(base64Data);
        var bytesLength = byteCharacters.length;
        var slicesCount = Math.ceil(bytesLength / sliceSize);
        var byteArrays = new Array(slicesCount);

        for (var sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
            var begin = sliceIndex * sliceSize;
            var end = Math.min(begin + sliceSize, bytesLength);

            var bytes = new Array(end - begin);
            for (var offset = begin, i = 0 ; offset < end; ++i, ++offset) {
                bytes[i] = byteCharacters[offset].charCodeAt(0);
            }
            byteArrays[sliceIndex] = new Uint8Array(bytes);
        }
        return new Blob(byteArrays, { type: contentType });
    }

    function blobToFile(blob){
        var filename = $scope.fileName
        if (typeof window.navigator.msSaveBlob !== 'undefined') {
            // IE workaround for "HTML7007: One or more blob URLs were revoked by
            //closing the blob for which they were created.
            //These URLs will no longer resolve as the data backing the URL has been freed."
            window.navigator.msSaveBlob(blob, filename);
        } else {
            var downloadUrl = URL.createObjectURL(blob);

            if (filename) {
                // use HTML5 a[download] attribute to specify filename
                var a = document.createElement("a");
                // safari doesn't support this yet
                if (typeof a.download === 'undefined') {
                    window.location = downloadUrl;
                } else {
                    a.href = downloadUrl;
                    a.download = filename;
                    document.body.appendChild(a);
                    a.click();
                }
            } else {
                window.location = downloadUrl;
            }

            setTimeout(function() {
                URL.revokeObjectURL(downloadUrl);
            }, 100); // cleanup
        }
    }

    $scope.reset = function() {
    	$scope.files = null;
        $scope.userType = angular.copy(userType);

        $scope.applicationUserTypeRoles = [];
        $scope.availableUserTypeRoleMappings = angular.copy(userTypeRoles);
        
        if ($scope.userType.userTypeRole) {
	        var roleNames = angular.copy($scope.userType.userTypeRole);
	        roleNames = roleNames.replace(/\"/g, '');
	        roleNames = roleNames.substring(1,roleNames .length-1);
	        roleNames = roleNames.split(",");
	        
	        for(var i = 0; i < roleNames.length; i++){
	        	for(var j = 0; j < $scope.availableUserTypeRoleMappings.length; j++){
	    			if ($scope.availableUserTypeRoleMappings[j].name.trim() == roleNames[i].trim()){
	    				$scope.applicationUserTypeRoles.push($scope.availableUserTypeRoleMappings[j]);
	    				$scope.availableUserTypeRoleMappings.splice(j, 1);
	    			}
	    		}
	        }        
        }
        
        $scope.applicationComposite = angular.copy($scope.applicationUserTypeRoles);
        $scope.changed = false;
    };

    $scope.cancel = function() {
        $location.url("/realms/" + realm.realm + "/user-types");
    };

    $scope.remove = function() {
        Dialog.confirmDelete($scope.userType.id, 'userType', function() {
            $scope.userType.$remove({
                realm : realm.realm,
                userType : $scope.userType.id
            }, function() {
                $location.url("/realms/" + realm.realm + "/user-types");
                Notifications.success("The user has been deleted.");
            }, function() {
                Notifications.error("User couldn't be deleted");
            });
        });
    };

});

module.controller('UserSubTypeListCtrl', function($scope, realm, UserSubType) {
    $scope.realm = realm;
    $scope.page = 0;

    $scope.query = {
        realm: realm.realm,
        max : 5,
        first : 0
    }

    $scope.firstPage = function() {
        $scope.query.first = 0;
        $scope.searchQuery();
    }

    $scope.previousPage = function() {
        $scope.query.first -= parseInt($scope.query.max);
        if ($scope.query.first < 0) {
            $scope.query.first = 0;
        }
        $scope.searchQuery();
    }

    $scope.nextPage = function() {
        $scope.query.first += parseInt($scope.query.max);
        $scope.searchQuery();
    }

    $scope.searchQuery = function() {
        console.log("query.search: " + $scope.query.search);
        $scope.searchLoaded = false;

        $scope.userSubTypes = UserSubType.query($scope.query, function() {
            $scope.searchLoaded = true;
            $scope.lastSearch = $scope.query.search;
        });
    };
});

module.controller('UserSubTypeDetailCtrl', function($scope, realm, userSubType, UserSubType, userTypes, $location, Dialog, Notifications) {
    $scope.realm = realm;
    $scope.userSubType = angular.copy(userSubType);
    $scope.create = !userSubType.name;

    $scope.changed = false; // $scope.create;

    $scope.$watch('userSubType', function() {
        if (!angular.equals($scope.userSubType, userSubType)) {
            $scope.changed = true;
        }
    }, true);

    //TODO get userTypeList userSubTypeList from server.
    $scope.userTypes =  angular.copy(userTypes);
    $scope.save = function() {
        if ($scope.create) {
            UserSubType.save({
                realm: realm.realm
            }, $scope.userSubType, function () {
                $scope.changed = false;
                userSubType = angular.copy($scope.userSubType);

                $location.url("/realms/" + realm.realm + "/user-sub-types/" + $scope.userSubType.name);
                Notifications.success("The user sub type has been created.");
            });
        } else {
            UserSubType.update({
                realm: realm.realm,
                userSubType: $scope.userSubType.id
            }, $scope.userSubType, function () {
                $scope.changed = false;
                userSubType = angular.copy($scope.userSubType);
                Notifications.success("Your changes have been saved to the user.");
            });
        }
    };

    $scope.reset = function() {
        $scope.userSubType = angular.copy(userSubType);
        $scope.changed = false;
    };

    $scope.cancel = function() {
        $location.url("/realms/" + realm.realm + "/user-sub-types");
    };

    $scope.remove = function() {
        Dialog.confirmDelete($scope.userSubType.id, 'userSubType', function() {
            $scope.userSubType.$remove({
                realm : realm.realm,
                userSubType : $scope.userSubType.id
            }, function() {
                $location.url("/realms/" + realm.realm + "/user-sub-types");
                Notifications.success("The user sub type has been deleted.");
            }, function() {
                Notifications.error("User sub type couldn't be deleted");
            });
        });
    };
});

module.controller('ReportCtrl', function($scope, realm) {
	$scope.realm = realm;
});

module.controller('ReportCtrl2', function($scope, realm) {
	$scope.realm = realm;
});
