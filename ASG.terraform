provider "aws"{
  region = "us-east-2"
}
resource "aws_launch_configuration" "as_conf" {
  name_prefix   = var.name_prefix
  image_id      = var.image_id
  instance_type = var.instance_type
  user_data = "${file("userdata.sh")}"
}

resource "aws_autoscaling_group" "bar" {
  name                 = var.name 
  launch_configuration = aws_launch_configuration.as_conf.name
  min_size             = 1
  max_size             = 2
  desired_capacity     = 1
 availability_zones = var.availability_zones
}
