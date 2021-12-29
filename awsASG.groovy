pipeline{
  agent {label 'main'}
  parameters{
    string (name: 'keyname', defaultValue: 'sshkey1', description: ' ')
            }
  satges{
    stage('Create Launch config'){
      steps{
         def cmd = "aws autoscaling create-launch-configuration --launch-configuration-name my-lc3-cli --image-id ami-0629230e074c580f2 --instance-type t2.micro --security-groups sg-017c097bb1674f881 --key-name " + keyname + " --user-data file://userdata.txt --region us-east-2"
        sh (cmd)
          } 
       }
    stage('ASG'){
      steps{
         sh "aws autoscaling create-auto-scaling-group --auto-scaling-group-name my-asg3-cli --launch-configuration-name my-lc3-cli --max-size 2 --min-size 1 --availability-zones us-east-2c --region us-east-2"
          } 
        }
  }
}
