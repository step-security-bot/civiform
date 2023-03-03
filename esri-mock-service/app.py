from flask import Flask

app = Flask(__name__)


@app.route("/findAddressCandidates")
def findAddressCandidates():
    jsonstr = """{
          "spatialReference": {
            "wkid": 4326,
            "latestWkid": 4326
          },
          "candidates": [
            {
              "address": "305 Harrison St, Seattle, WA, 98109",
              "location": {
                "x": -117.19479001927878,
                "y": 34.057264995787875
              },
              "score": 100,
              "attributes": {
                "SubAddr": "",
                "Address": "305 Harrison St",
                "City": "Seattle",
                "RegionAbbr": "WA",
                "Postal": "98109"
              },
              "extent": {
                "xmin": -117.19579001927879,
                "ymin": 34.056264995787878,
                "xmax": -117.19379001927878,
                "ymax": 34.058264995787873
              }
            },
            {
              "address": "Harrison St, Seattle, WA, 98109",
              "location": {
                "x": -117.19479001927878,
                "y": 34.057264995787875
              },
              "score": 100,
              "attributes": {
                "SubAddr": "",
                "Address": "Harrison St",
                "City": "Seattle",
                "RegionAbbr": "WA",
                "Postal": "98109"
              },
              "extent": {
                "xmin": -117.19579001927879,
                "ymin": 34.056264995787878,
                "xmax": -117.19379001927878,
                "ymax": 34.058264995787873
              }
            },
            {
              "address": "305 W Harrison St, Seattle, WA, 98119",
              "location": {
                "x": -117.19479001927878,
                "y": 34.057264995787875
              },
              "score": 100,
              "attributes": {
                "SubAddr": "",
                "Address": "305 W Harrison St",
                "City": "Seattle",
                "RegionAbbr": "WA",
                "Postal": "98119"
              },
              "extent": {
                "xmin": -117.19579001927879,
                "ymin": 34.056264995787878,
                "xmax": -117.19379001927878,
                "ymax": 34.058264995787873
              }
            }
          ]
        }"""

    response = app.response_class(response=jsonstr, mimetype="application/json")

    return response


@app.route("/serviceAreaFeatures")
def serviceAreaFeatures():
    jsonstr = """{
          "displayFieldName": "JURIS",
          "fieldAliases": {
            "OBJECTID": "OBJECTID",
            "JURIS": "JURIS",
            "SE_ANNO_CAD_DATA": "SE_ANNO_CAD_DATA",
            "CITYNAME": "CITYNAME",
            "GIS_AREA": "GIS_AREA",
            "GIS_LENGTH": "GIS_LENGTH",
            "SHAPE_Length": "SHAPE_Length",
            "SHAPE_Area": "SHAPE_Area"
          },
          "fields": [
            {
              "name": "OBJECTID",
              "type": "esriFieldTypeOID",
              "alias": "OBJECTID"
            },
            {
              "name": "JURIS",
              "type": "esriFieldTypeString",
              "alias": "JURIS",
              "length": 2
            },
            {
              "name": "SE_ANNO_CAD_DATA",
              "type": "esriFieldTypeBlob",
              "alias": "SE_ANNO_CAD_DATA"
            },
            {
              "name": "CITYNAME",
              "type": "esriFieldTypeString",
              "alias": "CITYNAME",
              "length": 75
            },
            {
              "name": "GIS_AREA",
              "type": "esriFieldTypeDouble",
              "alias": "GIS_AREA"
            },
            {
              "name": "GIS_LENGTH",
              "type": "esriFieldTypeDouble",
              "alias": "GIS_LENGTH"
            },
            {
              "name": "SHAPE_Length",
              "type": "esriFieldTypeDouble",
              "alias": "SHAPE_Length"
            },
            {
              "name": "SHAPE_Area",
              "type": "esriFieldTypeDouble",
              "alias": "SHAPE_Area"
            }
          ],
          "features": [
            {
              "attributes": {
                "OBJECTID": 50,
                "JURIS": "SE",
                "SE_ANNO_CAD_DATA": null,
                "CITYNAME": "Seattle",
                "GIS_AREA": null,
                "GIS_LENGTH": null,
                "SHAPE_Length": 341837.02272776031,
                "SHAPE_Area": 3997734494.6939893
              }
            }
          ]
        }"""

    return app.response_class(response=jsonstr, mimetype="application/json")


if __name__ == "__main__":
    app.debug = True
    app.run(host="0.0.0.0", port=8000)
