from django.db import models

# Create your models here.

class Ranger(models.Model):
    name = models.CharField(max_length=200, default=1)

    def __str__(self):
        return self.name

class Ask(models.Model):
    author = models.ForeignKey(Ranger, on_delete=models.CASCADE)
    content = models.CharField(max_length=3000, default=1)
    cheers = models.IntegerField(default=0)

    def __str__(self):
        return self.content

class Answer(models.Model):
    ask = models.ForeignKey(Ask, on_delete=models.CASCADE)
    author = models.ForeignKey(Ranger, on_delete=models.CASCADE)
    answer = models.CharField(max_length = 5000, default = 1)
    upvotes = models.IntegerField(default=0)

    def __str__(self):
        return self.answer