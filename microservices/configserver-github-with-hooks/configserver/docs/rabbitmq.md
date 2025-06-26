# RabbitMQ

I need rabbitmq because it's a dependency of the Spring Cloud Bus.  
I need Spring Cloud Bus because I have a problem:
- Currently, whenever I change a configuration inside the GitHub repository, I'll have to POST the actuator "/refresh"
  for every microservice I have. I can have 500 microservices.
- The solution is Spring Cloud Bus.

## Installation

```shell
# latest RabbitMQ 4.x
docker container run -d -it --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management
```

## Usage

You need to hit actuator '/busrefresh' instead of '/refresh'  
The '/refresh' used to refresh just the single microservice.  
The '/busrefresh' will refresh all the microservices.  
You can still use '/refresh' to refresh a single microservice, but why would you?

## Another Step Forward

I don't even want to hit the '/busrefresh' even once.  
For that, I need Spring Cloud Config Monitor in the config server and the actuator (it was not needed here till now,
only in the microservices for calling '/busrefresh').  
Now, the idea is to use a GitHub webhook to hit '/monitor' in the config server when a file changes. 
You can't put localhost in the webhook, so you use "https://hookdeck.com"  

I am not writing the configuration steps because they keep on changing.  
Just keep following the official documentation, and eventually it will work out.  
Generally, this is what I did:
1. created an account via GitHub
2. created a new connection (chose localhost, not http, and choose `github` in connection type or something)  
   because you will use `github` as Source in the listen command
3. installed hookdeck-cli via npm (it's written in the documentation, but this won't change so: `npm i -g hookdeck-cli`)
4. `hookdeck login --cli-key 2zmvi36vvhygh94kwese2pyehc5f768gjjob77c67r187rlt7o` -- login
5. `hookdeck listen 8071 github`