/**
 * Created by 605752564 on 14/04/2016.
 */
(function () {
    'use strict';

    angular
        .module('drillTheDataApp')
        .factory('GTDCharts', GTDCharts);

    GTDCharts.$inject = [];

    function GTDCharts() {
        var gtdCharts = {};
        var data = null;
        var incidentsMap = null;
        var incidents = null;
        var incidentsGroup = null;


        gtdCharts.makeGraphs = function (apiData) {
            data = apiData;

            setTimeout(function () {
                var ndx = crossfilter(data);

                var incidentsCount = ndx.groupAll().reduceSum(function (d) {
                    return 1;
                });

                var incidentsCountChart = dc.numberDisplay("#incidents-count-display");

                incidentsCountChart
                    .formatNumber(d3.format("d"))
                    .valueAccessor(function (d) {
                        return d;
                    })
                    .group(incidentsCount)
                    .formatNumber(d3.format("s"));


                var killsGroup = ndx.groupAll().reduceSum(function (d) {
                    if (d.numberOfKills.length == 0)
                    {
                        return 0;
                    }
                    else {
                        return parseInt(d.numberOfKills);
                    }
                });

                var killsCountChart = dc.numberDisplay("#kills-count-display");

                killsCountChart
                    .formatNumber(d3.format("d"))
                    .valueAccessor(function (d) {
                        return d;
                    })
                    .group(killsGroup)
                    .formatNumber(d3.format("s"));

                var injuriesGroup = ndx.groupAll().reduceSum(function (d) {
                    if (d.numberOfWounded.length == 0)
                    {
                        return 0;
                    }
                    else {
                        return parseInt(d.numberOfWounded);
                    }
                });

                var injuriesCountChart = dc.numberDisplay("#injured-count-display");

                injuriesCountChart
                    .formatNumber(d3.format("d"))
                    .valueAccessor(function (d) {
                        return d;
                    })
                    .group(injuriesGroup)
                    .formatNumber(d3.format("s"));

                var perpetratorsGroup = ndx.groupAll().reduceSum(function (d) {
                    return d.numberOfPerpetrators;
                });

                var perpetratorsCountChart = dc.numberDisplay("#perpetrators-count-display");

                perpetratorsCountChart
                    .formatNumber(d3.format("d"))
                    .valueAccessor(function (d) {
                        return d;
                    })
                    .group(perpetratorsGroup)
                    .formatNumber(d3.format("s"));

                var damagesGroup = ndx.groupAll().reduceSum(function (d) {
                    if (d.valueOfDamageInUSD.length == 0)
                    {
                        return 0;
                    }
                    else {
                        var damage = parseInt(d.valueOfDamageInUSD);
                        if(damage > 0)
                        {
                            return damage;
                        }

                        return 0;
                    }
                });

                var damagesCountChart = dc.numberDisplay("#damages-sum-display");

                damagesCountChart
                    .formatNumber(d3.format("$,"))
                    .valueAccessor(function (d) {
                        return d;
                    })
                    .group(damagesGroup)
                    .formatNumber(d3.format("$,"));

                var incidentVolumeWindow = ndx.dimension(function (d) {
                    var day, month, year;

                    if(d.day == '0')
                    {
                        day = 1;
                    }
                    else {
                        day = parseInt(d.day);
                    }

                    if(d.month == '0')
                    {
                        month = parseInt(d.month);
                    }
                    else {
                        month = parseInt(d.month) - 1;
                    }

                    year = parseInt(d.year);

                    var date = new Date(year, month, day);
                    return date;
                });

                var incidentVolumeGroup = incidentVolumeWindow.group().reduceSum(function (d) {
                    // return d.eventId;
                    var wounded;
                    var killed;

                    if (d.numberOfWounded.length == 0)
                    {
                        wounded = 0;
                    }
                    else {
                        wounded =  parseInt(d.numberOfWounded);
                    }

                    if (d.numberOfKills.length == 0)
                    {
                        killed = 0;
                    }
                    else {
                        killed = parseInt(d.numberOfKills);
                    }
                    return killed + wounded;
                });


                var volumeChart = dc.barChart("#incident-volume-chart");
                volumeChart
                    .height(400)
                    .margins({top: 10, right: 50, bottom: 30, left: 50})
                    .dimension(incidentVolumeWindow)
                    .group(incidentVolumeGroup)
                    .centerBar(true)
                    .gap(10)
                    .elasticX(true)
                    .elasticY(true)
                    .renderHorizontalGridLines(true)
                    .renderVerticalGridLines(true)
                    .x(d3.time.scale())
                    .xUnits(d3.time.days)
                    .xAxis().ticks(50);

                var countryDimension = ndx.dimension(function (d) {
                    return d.country;
                });

                var countryGroup = countryDimension.group().reduceCount(function (d) {
                    return d.eventId;
                });

                var countryChart = dc.barChart("#country-chart");
                countryChart
                    .height(500)
                    .transitionDuration(1000)
                    .dimension(countryDimension)
                    .group(countryGroup)
                    // .margins({top: 10, right: 50, bottom: 30, left: 50})
                    .centerBar(false)
                    .gap(5)
                    .elasticX(true)
                    .elasticY(true)
                    .x(d3.scale.ordinal().domain(countryDimension))
                    .xUnits(dc.units.ordinal)
                    .renderHorizontalGridLines(false)
                    .renderVerticalGridLines(false)
                    .yAxis().tickFormat(d3.format("s"));

                var regionDimension = ndx.dimension(function (d) {
                    return d.region;
                });

                var regionGroup = regionDimension.group().reduceCount(function (d) {
                    return d.eventId;
                });

                var regionChart = dc.barChart("#region-chart");
                regionChart
                    .height(500)
                    .transitionDuration(1000)
                    .dimension(regionDimension)
                    .group(regionGroup)
                    // .margins({top: 10, right: 50, bottom: 30, left: 50})
                    .centerBar(false)
                    .gap(5)
                    .elasticX(true)
                    .elasticY(true)
                    .x(d3.scale.ordinal().domain(regionDimension))
                    .xUnits(dc.units.ordinal)
                    .renderHorizontalGridLines(false)
                    .renderVerticalGridLines(false)
                    .yAxis().tickFormat(d3.format("s"));

                var cityDimension = ndx.dimension(function (d) {
                    return d.city;
                });

                var cityGroup = cityDimension.group().reduceCount(function (d) {
                    return d.eventId;
                });

                // var cityChart = dc.rowChart("#city-chart");
                // cityChart
                //     .height(1500)
                //     .transitionDuration(1000)
                //     .dimension(cityDimension)
                //     .group(cityGroup)
                //     .elasticX(true)
                //     .ordering(function (d) {
                //         return -d.value;
                //     })
                //     .xAxis().ticks(10);

                $('#incidentsMap .map.card').empty();
                incidentsMap = dc_leaflet.markerChart('#incidentsMap .map.card');
                incidentsMap.mapOptions({
                    touchZoom: false,
                    // doubleClickZoom: false,
                    scrollWheelZoom: false
                });

                incidents = ndx.dimension(function (d) {
                    return [d.latitude, d.longitude];
                });

                incidentsGroup = incidents.group().reduceCount(function (d) {
                    return d.city;
                });

                incidentsMap
                    .dimension(incidents)
                    .group(incidentsGroup)
                    // .width(770)
                    .height(600)
                    .center([51.50, 0.1278])
                    .zoom(7)
                    .cluster(true);


                var targetDimension = ndx.dimension(function (d) {
                    return d.targetType;
                });

                var targetGroup = targetDimension.group().reduceCount(function (d) {
                    return d.eventId;
                });

                var targetChart = dc.rowChart("#target-chart");
                targetChart
                    .height(500)
                    .transitionDuration(1000)
                    .dimension(targetDimension)
                    .group(targetGroup)
                    .elasticX(true)
                    .ordering(function (d) {
                        return -d.value;
                    })
                    .xAxis().ticks(10);

                var targetNationalitiesDimension = ndx.dimension(function (d) {
                    return d.nationality; //for selected target
                });

                var targetNationalitiesGroup = targetNationalitiesDimension.group().reduceCount(function (d) {
                    return d.eventId;
                });

                function remove_empty_bins(source_group) {
                    return {
                        all:function () {
                            return source_group.all().filter(function(d) {
                                return d.value != 0;
                            });
                        }
                    };
                }

                var nonEmptyTargetNationalitiesGroup = remove_empty_bins(targetNationalitiesGroup);

                var targetNationalitiesChart = dc.rowChart("#targetNationalities-chart");
                targetNationalitiesChart
                    .height(500)
                    .transitionDuration(1000)
                    .dimension(targetNationalitiesDimension)
                    .group(nonEmptyTargetNationalitiesGroup)
                    .elasticX(true)
                    .ordering(function (d) {
                        return -d.value;
                    })
                    .xAxis().ticks(10);

                var groupResponsibleDimension = ndx.dimension(function (d) {
                    return d.groupResponsible;
                });

                var groupResponsibleGroup = groupResponsibleDimension.group().reduceCount(function (d) {
                    return d.eventId;
                });

                var nonEmptyGroupResponsibleGroup = remove_empty_bins(groupResponsibleGroup);

                var groupResponsibleChart = dc.rowChart("#groupResponsible-chart");
                groupResponsibleChart
                    .height(2000)
                    .transitionDuration(1000)
                    .dimension(groupResponsibleDimension)
                    .group(nonEmptyGroupResponsibleGroup)
                    .elasticX(true)
                    .ordering(function (d) {
                        return -d.value;
                    })
                    // .fixedBarHeight((800 - (groupResponsibleDimension.length + 1) * 5 - 70) / groupResponsibleDimension.length)
                    .xAxis().ticks(10);

                var weaponsUsedDimension = ndx.dimension(function (d) {
                    return d.weapon;
                });

                var weaponUsedGroup = weaponsUsedDimension.group().reduceCount(function (d) {
                    return d.eventId;
                });

                var nonEmptyWeaponUsedGroup = remove_empty_bins(weaponUsedGroup);

                var weaponUsedChart = dc.rowChart("#weapons-chart");
                weaponUsedChart
                    .height(2000)
                    .transitionDuration(1000)
                    .dimension(weaponsUsedDimension)
                    .group(weaponUsedGroup)
                    .elasticX(true)
                    .ordering(function (d) {
                        return -d.value;
                    })
                    // .fixedBarHeight((800 - (groupResponsibleDimension.length + 1) * 5 - 70) / groupResponsibleDimension.length)
                    .xAxis().ticks(10);

                dc.renderAll();
            }, 1);
        };

        gtdCharts.renderAll = function () {
            console.log("Rendering charts");
            if ( $( "#incidentsMap .map.card" ).length ) {
                $('#incidentsMap').empty();
                $('#incidentsMap').append('<div class="map card"> </div>');
                incidentsMap = dc_leaflet.markerChart('#incidentsMap .map.card');
                incidentsMap.mapOptions({
                    touchZoom: false,
                    // doubleClickZoom: false,
                    scrollWheelZoom: false
                });
                incidentsMap
                    .dimension(incidents)
                    .group(incidentsGroup)
                    // .width(770)
                    .height(600)
                    .center([51.50, 0.1278])
                    .zoom(7)
                    .cluster(true);
                dc.renderAll();
            }
        };

        return gtdCharts;
    }

})();
