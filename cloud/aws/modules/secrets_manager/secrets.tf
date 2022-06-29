# Importing the AWS secrets created previously using name.
data "aws_secretsmanager_secret" "postgres_username" {
  name = aws_secretsmanager_secret.postgres_username_secret.name
  depends_on = [
    aws_secretsmanager_secret.postgres_username_secret
  ]
}

# Importing the AWS secret version created previously using arn.
data "aws_secretsmanager_secret_version" "postgres_username_version" {
  secret_id = data.aws_secretsmanager_secret.postgres_username.arn
}

# Importing the AWS secrets created previously using name.
data "aws_secretsmanager_secret" "postgres_password" {
  name = aws_secretsmanager_secret.postgres_password_secret.name
  depends_on = [
    aws_secretsmanager_secret.postgres_password_secret
  ]
}

# Importing the AWS secret version created previously using arn.
data "aws_secretsmanager_secret_version" "postgres_password_version" {
  secret_id = data.aws_secretsmanager_secret.postgres_password.arn
}

# Importing the AWS secrets created previously using name.
data "aws_secretsmanager_secret" "app_secret_key" {
  name = aws_secretsmanager_secret.app_secret_key_secret.name
  depends_on = [
    aws_secretsmanager_secret.app_secret_key_secret
  ]
}

# Importing the AWS secret version created previously using arn.
data "aws_secretsmanager_secret_version" "app_secret_key_version" {
  secret_id = data.aws_secretsmanager_secret.app_secret_key.arn
}

# Importing the AWS secrets created previously using name.
data "aws_secretsmanager_secret" "adfs_secret" {
  name = aws_secretsmanager_secret.adfs_secret_secret.name
  depends_on = [
    aws_secretsmanager_secret.adfs_secret_secret
  ]
}

# Importing the AWS secret version created previously using arn.
data "aws_secretsmanager_secret_version" "adfs_secret_version" {
  secret_id = data.aws_secretsmanager_secret.adfs_secret.arn
}
