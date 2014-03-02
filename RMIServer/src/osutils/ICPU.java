/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osutils;

/**
 *
 * @author cynthia
 */
public interface ICPU {
    public float getTotalUsed();
    public float getUserLoad();
    public float getSystemLoad();
    public float getIdle();
}
