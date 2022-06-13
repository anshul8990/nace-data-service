### nace-data-service 

##Modules
 
  * nace-application - contains application & controllers 
  * nace-commons - contains common dto's to restrict the view of domain 
  * nace-config - properties file ,yml's
  * nace-persistance - persistance layer
  * nace-service - business logics     

#Swagger-UI

http://localhost:8080/swagger-ui/index.html

#Postman 
# Get
*localhost:8080/nace-data/{orderid}* 
*response :* 200(Ok) 
   * json object with nase details *
            `  {`
            `   "order": "398481",`
            `   "level": "1",`
            `   "code": "A",`
            `   "parent": "",`
            `   "description": "AGRICULTURE, FORESTRY AND FISHING",`
            `   "reference": "A"`
            `   }`

#Post 

*localhost:8080/nace-data*
 
 *response :*  201(created) 

# Sample curl for post 
 
 curl --location --request POST 'localhost:8080/nace-data' \
  --form 'file=@"/D:/Anshul/eclipse/workspace/nace-data-service/nace-application/src/main/resources/nacedata/NACE_REV2_20220612_135646.csv"'
