pipeline{
  agent {label 'main'}
  parameters{
    string(name: 'keyname',defaultValue: 'sshkey1')
    string(name: 'count', defaulValue:'1')
            }
  stages{
    stage('creating ami'){
      environment{
        
      steps{
         def cmd = "aws ec2 create-image --instance-id i-033e9c4279b6714ba --name myami1 --region us-east-2"
          def output = sh(script: cmd,returnStdout: true)
         jsonitem = readJSON text: output
         println(jsonitem)
         sleep(180)
          }
        }
    stage('Launch ec2'){
      steps{
        def cmd = "aws ec2 run-instances --image-id ${jsonitem['ImageId']} --count "+count+" --instance-type t2.micro --key-name "+keyname+" --security-group-ids sg-017c097bb1674f881 --subnet-id subnet-0a22ca2d020ca46c1 --region us-east-2"
        def output = sh(script:cmd,returnStdout: true)
        jsonitem1 = readJSON text: output
        println(jsonitem)
        sleep(60)
           }
         }
    stage('terminate ec2'){
      steps{
                sh "aws ec2 terminate-instances --instance-ids ${jsonitem1['Instances'][0]['InstanceId']} --region us-east-2"
           }
        }
    stage('deregister ami'){
      steps{
                sh "aws ec2 deregister-image --image-id ${jsonitem['ImageId']} --region us-east-2"
            }
          }
  }
}
