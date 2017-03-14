(function() {
    'use strict';

    angular
        .module('drillTheDataApp')
        .factory('SocialService', SocialService);

    SocialService.$inject = ['$http', '$cookies'];

    function SocialService ($http, $cookies) {
        var socialService = {
            getProviderSetting: getProviderSetting,
            getProviderURL: getProviderURL,
            getCSRF: getCSRF
        };

        return socialService;

        function getProviderSetting (provider) {
            switch(provider) {
            case 'google': return 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email';
            case 'facebook':return 'id,about,age_range,birthday,context,cover,currency,devices,education,email,favorite_athletes,favorite_teams,first_name,gender,hometown,inspirational_people,installed,install_type,is_verified,languages,last_name,link,locale,location,meeting_for,middle_name,name,name_format,political,quotes,payment_pricepoints,relationship_status,religion,security_settings,significant_other,sports,test_group,timezone,third_party_id,updated_time,verified,video_upload_limits,viewer_can_send_gift,website,work';
            case 'twitter': return '';
                // jhipster-needle-add-social-button
            default: return 'Provider setting not defined';
            }
        }

        function getProviderURL (provider) {
            return 'signin/' + provider;
        }

        function getCSRF () {
            return $cookies.get($http.defaults.xsrfCookieName);
        }
    }
})();
