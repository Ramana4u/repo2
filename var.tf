variable "name_prefix" {
  type = string
  default = "terraform-lc-example-"
}
variable "image_id" {
  type = string
  default = "ami-0629230e074c580f2"
}
variable "instance_type" {
  type = string
  default = "t2.micro"
}
variable "name" {
  type = string
  default = "terraform-asg-example"
}
variable "availability_zones" {
  type = list(string)
  default = ["us-east-2c"]
}
