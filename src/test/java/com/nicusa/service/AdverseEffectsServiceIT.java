package com.nicusa.service;

import com.nicusa.UiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;

import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UiApplication.class)
public class AdverseEffectsServiceIT
{
  Logger log = LoggerFactory.getLogger(AdverseEffectsServiceIT.class);

  @PersistenceContext
  EntityManager entityManager;

  @Autowired
  private AdverseEffectService adverseEffectService;

  @Test
  public void testFindEffectDescription() throws IOException {
    String desc = adverseEffectService.findEffectDescription( "CEREBROVASCULAR ACCIDENT" );
    assertTrue( desc.contains( "cerebro" ));
  }

}
