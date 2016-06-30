package br.com.fitrank.job;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.fitrank.service.PostFitnessServico;
import br.com.fitrank.util.ConstantesFitRank;
import br.com.fitrank.util.Logger;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient.AccessToken;

public class JobExtracao implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Logger.insertLog("Job iniciado");
		
		InputStream input = null;
	    Properties prop = new Properties();
	    PostFitnessServico postFitnessServico = new PostFitnessServico();
	    
	    input = getClass().getClassLoader().getResourceAsStream("config.properties");
	   
	    try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    AccessToken accessToken = new DefaultFacebookClient().obtainAppAccessToken(ConstantesFitRank.ID_APP_FITRANK, prop.getProperty("app_secret"));
	    
//	    postFitnessServico
	    
	    
	}
}
